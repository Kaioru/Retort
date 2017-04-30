package co.kaioru.retort.builder

import co.kaioru.retort.Command
import co.kaioru.retort.CommandContext
import co.kaioru.retort.ICommand
import co.kaioru.retort.ICommandExecutable

class CommandBuilder<I : CommandContext, O>(name: String) : Command<I, O>(name), ICommandBuilder<I, O> {
    var executable: ICommandExecutable<I, O>? = null

    override fun build(executable: ICommandExecutable<I, O>): ICommand<I, O> {
        this.executable = executable
        return this
    }

    override fun execute(input: I): O {
        if (executable == null)
            TODO()
        return executable?.execute(input)!!
    }
}