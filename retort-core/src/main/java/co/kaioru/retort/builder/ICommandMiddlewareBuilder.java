package co.kaioru.retort.builder;

import co.kaioru.retort.ICommandContext;
import co.kaioru.retort.ICommandExecutable;
import co.kaioru.retort.ICommandMiddleware;

public interface ICommandMiddlewareBuilder<I extends ICommandContext> {

	ICommandMiddlewareBuilder<I> withName(String name);

	ICommandMiddlewareBuilder<I> withDescription(String description);

	ICommandMiddleware<I> build(ICommandExecutable<I, Boolean> executable);

}
