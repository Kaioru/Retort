package co.kaioru.retort.impl

import co.kaioru.retort.ICommand
import co.kaioru.retort.ICommandMiddleware
import co.kaioru.retort.exceptions.CommandMiddlewareException


abstract class Command<I : CommandContext, O>(override val name: String) : ICommand<I, O> {
    override var description: String = "No description"
    override val aliases: MutableCollection<String> = HashSet()
    override val middleware: MutableCollection<ICommandMiddleware<I>> = HashSet()
    override val commands: MutableCollection<ICommand<I, O>> = HashSet()
    val regex: String = "([\"'])(?:(?=(\\\\?))\\2.)*?\\1|([^\\s]+)"

    override fun process(input: I, text: String): O {
        val args = input.args
        val pattern = java.util.regex.Pattern.compile(regex)
        val matcher = pattern.matcher(text)

        while (matcher.find()) {
            var string = matcher.group()

            if ((string.startsWith("'") && string.endsWith("'")) || (string.startsWith("\"") && string.endsWith("\"")))
                string = string.substring(1, string.length - 1)
            args.add(string)
        }
        return process(input)
    }

    override fun process(input: I): O {
        val args = input.args

        if (args.size > 0) {
            val first = args.peek()
            val commands = getCommand(first)
            val opt = commands
                    .stream()
                    .sorted { o1, o2 ->
                        o1.name.compareTo(first).compareTo(o2.name.compareTo(first)) }
                    .findFirst()
            if (opt.isPresent) {
                args.remove()
                return opt.get().process(input)
            }
        }

        if (!middleware.asSequence().all { it.execute(input) })
            throw CommandMiddlewareException()

        return execute(input)
    }

    fun getCommand(name: String): Collection<ICommand<I, O>> {
        return commands.asSequence()
                .filter {
                    it.name.toLowerCase().startsWith(name) || it.aliases.asSequence().any { it.toLowerCase().startsWith(name) }
                }
                .toHashSet()
    }
}