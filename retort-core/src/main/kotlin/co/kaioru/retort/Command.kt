package co.kaioru.retort

import java.util.*
import java.util.regex.Pattern


abstract class Command<I : CommandContext, O>(override val name: String) : ICommand<I, O> {
    override var description: String = "No description"
    override val aliases: MutableCollection<String> = HashSet()
    override val middleware: MutableCollection<ICommandMiddleware<I>> = HashSet()
    override val commands: MutableCollection<ICommand<I, O>> = HashSet()
    val regex: String = "([\"'])(?:(?=(\\\\?))\\2.)*?\\1|([^\\s]+)"

    override fun process(input: I, text: String): O {
        val args = input.args
        val pattern = Pattern.compile(regex)
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
            val opt = getCommand(first)
                    .stream()
                    .findFirst()
            if (opt.isPresent) {
                args.remove()
                return opt.get().process(input)
            }
        }

        if (!middleware.asSequence().all { it.execute(input) }) {
            TODO()
        }

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