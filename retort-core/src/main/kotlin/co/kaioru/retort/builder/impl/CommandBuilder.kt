package co.kaioru.retort.builder.impl

import co.kaioru.retort.ICommand
import co.kaioru.retort.ICommandExecutable
import co.kaioru.retort.builder.ICommandBuilder
import co.kaioru.retort.exceptions.CommandNotBuiltException
import co.kaioru.retort.impl.Command
import co.kaioru.retort.impl.CommandContext

class CommandBuilder<I : CommandContext, O>(name: String) : Command<I, O>(name), ICommandBuilder<I, O> {
    var executable: ICommandExecutable<I, O>? = null

    override fun build(executable: ICommandExecutable<I, O>): ICommand<I, O> {
        this.executable = executable
        return this
    }

    override fun execute(input: I): O {
        if (executable == null)
            throw CommandNotBuiltException()
        return executable?.execute(input)!!
    }
}