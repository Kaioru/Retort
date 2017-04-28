package co.kaioru.retort.builder;

import co.kaioru.retort.ICommand;
import co.kaioru.retort.ICommandContext;
import co.kaioru.retort.ICommandExecutable;
import co.kaioru.retort.ICommandMiddleware;

import java.util.Collection;

public interface ICommandBuilder<I extends ICommandContext, O> {

	ICommandBuilder<I, O> withDescription(String description);

	ICommandBuilder<I, O> withAlias(String alias);

	ICommandBuilder<I, O> withAliases(Collection<? extends String> aliases);

	ICommandBuilder<I, O> withMiddleware(ICommandMiddleware<I> middleware);

	ICommandBuilder<I, O> withMiddlewares(Collection<? extends ICommandMiddleware<I>> middleware);

	ICommandBuilder<I, O> withCommand(ICommand<I, O> command);

	ICommandBuilder<I, O> withCommands(Collection<? extends ICommand<I, O>> commands);

	ICommand<I, O> build(ICommandExecutable<I, O> executable);

}
