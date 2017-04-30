package co.kaioru.retort

interface ICommand<I, O> : ICommandExecutable<I, O> {
    val name: String
    var description: String
    val aliases: MutableCollection<String>
    val middleware: MutableCollection<ICommandMiddleware<I>>
    val commands: MutableCollection<ICommand<I, O>>

    fun registerAlias(alias: String) {
        this.aliases.add(alias)
    }

    fun registerAliases(aliases: Collection<String>) {
        this.aliases.addAll(aliases)
    }

    fun registerMiddleware(middleware: ICommandMiddleware<I>) {
        this.middleware.add(middleware)
    }

    fun registerMiddleware(middleware: Collection<ICommandMiddleware<I>>) {
        this.middleware.addAll(middleware)
    }

    fun registerCommand(command: ICommand<I, O>) {
        this.commands.add(command)
    }

    fun registerCommands(commands: Collection<ICommand<I, O>>) {
        this.commands.addAll(commands)
    }

    fun process(input: I): O
    fun process(input: I, text: String): O
}