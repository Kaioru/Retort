package co.kaioru.retort.builder

import co.kaioru.retort.CommandContext
import co.kaioru.retort.CommandMiddleware
import co.kaioru.retort.ICommandExecutable
import co.kaioru.retort.ICommandMiddleware

class MiddlewareBuilder<I : CommandContext> : CommandMiddleware<I>(), IMiddlewareBuilder<I> {
    var executable: ICommandExecutable<I, Boolean>? = null

    override fun build(executable: ICommandExecutable<I, Boolean>): ICommandMiddleware<I> {
        this.executable = executable
        return this
    }

    override fun execute(input: I): Boolean {
        if (executable == null)
            TODO()
        return executable?.execute(input)!!
    }
}