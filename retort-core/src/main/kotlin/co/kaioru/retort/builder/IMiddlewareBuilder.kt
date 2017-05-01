package co.kaioru.retort.builder

import co.kaioru.retort.ICommandExecutable
import co.kaioru.retort.ICommandMiddleware

interface IMiddlewareBuilder<I> : ICommandMiddleware<I> {
    fun build(executable: ICommandExecutable<I, Boolean>): ICommandMiddleware<I>
}