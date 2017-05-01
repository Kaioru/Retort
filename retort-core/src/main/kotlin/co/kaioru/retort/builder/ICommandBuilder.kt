package co.kaioru.retort.builder

import co.kaioru.retort.ICommand
import co.kaioru.retort.ICommandExecutable
import co.kaioru.retort.ICommandMiddleware

interface ICommandBuilder<I, O> : ICommand<I, O> {
    fun withDescription(description: String): ICommandBuilder<I, O> {
        this.description = description
        return this
    }

    fun withAlias(alias: String): ICommandBuilder<I, O> {
        registerAlias(alias)
        return this
    }

    fun withAliases(aliases: Collection<String>): ICommandBuilder<I, O> {
        registerAliases(aliases)
        return this
    }

    fun withMiddleware(middleware: ICommandMiddleware<I>): ICommandBuilder<I, O> {
        registerMiddleware(middleware)
        return this
    }

    fun withMiddleware(middleware: Collection<ICommandMiddleware<I>>): ICommandBuilder<I, O> {
        registerMiddleware(middleware)
        return this
    }

    fun withCommand(command: ICommand<I, O>): ICommandBuilder<I, O> {
        registerCommand(command)
        return this
    }

    fun withCommands(commands: Collection<ICommand<I, O>>): ICommandBuilder<I, O> {
        registerCommands(commands)
        return this
    }

    fun build(executable: ICommandExecutable<I, O>): ICommand<I, O>
}