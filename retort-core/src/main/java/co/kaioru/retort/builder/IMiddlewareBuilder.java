package co.kaioru.retort.builder;

import co.kaioru.retort.ICommandContext;
import co.kaioru.retort.ICommandExecutable;
import co.kaioru.retort.ICommandMiddleware;

public interface IMiddlewareBuilder<I extends ICommandContext> {

    IMiddlewareBuilder<I> withName(String name);

    IMiddlewareBuilder<I> withDescription(String description);

    ICommandMiddleware<I> build(ICommandExecutable<I, Boolean> executable);

}
