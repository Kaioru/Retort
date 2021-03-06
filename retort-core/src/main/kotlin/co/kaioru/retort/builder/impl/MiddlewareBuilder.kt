package co.kaioru.retort.builder.impl

import co.kaioru.retort.ICommandExecutable
import co.kaioru.retort.ICommandMiddleware
import co.kaioru.retort.builder.IMiddlewareBuilder
import co.kaioru.retort.exceptions.CommandNotBuiltException
import co.kaioru.retort.impl.CommandContext
import co.kaioru.retort.impl.CommandMiddleware

open class MiddlewareBuilder<I : CommandContext> : CommandMiddleware<I>(), IMiddlewareBuilder<I> {
    var executable: ICommandExecutable<I, Boolean>? = null

    override fun build(executable: ICommandExecutable<I, Boolean>): ICommandMiddleware<I> {
        this.executable = executable
        return this
    }

    override fun execute(input: I): Boolean {
        if (executable == null)
            throw CommandNotBuiltException()
        return executable?.execute(input)!!
    }
}