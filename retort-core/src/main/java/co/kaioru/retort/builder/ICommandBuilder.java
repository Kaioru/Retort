package co.kaioru.retort.builder;

import co.kaioru.retort.ICommand;
import co.kaioru.retort.ICommandContext;
import co.kaioru.retort.ICommandExecutable;
import co.kaioru.retort.ICommandMiddleware;

public interface ICommandBuilder<I extends ICommandContext, O> {

	ICommandBuilder withDescription(String description);

	ICommandBuilder withAlias(String alias);

	ICommandBuilder withMiddleware(ICommandMiddleware<I> middleware);

	ICommandBuilder withCommand(ICommand<I, O> command);

	ICommand<I, O> build(ICommandExecutable<I, O> executable);

}
