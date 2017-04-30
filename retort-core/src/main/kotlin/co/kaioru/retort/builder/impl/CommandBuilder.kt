package co.kaioru.retort.builder.impl

import co.kaioru.retort.impl.Command
import co.kaioru.retort.impl.CommandContext

class CommandBuilder<I : CommandContext, O>(name: String) : Command<I, O>(name), co.kaioru.retort.builder.ICommandBuilder<I, O> {
    var executable: co.kaioru.retort.ICommandExecutable<I, O>? = null

    override fun build(executable: co.kaioru.retort.ICommandExecutable<I, O>): co.kaioru.retort.ICommand<I, O> {
        this.executable = executable
        return this
    }

    override fun execute(input: I): O {
        if (executable == null)
            TODO()
        return executable?.execute(input)!!
    }
}