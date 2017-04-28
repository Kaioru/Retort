package co.kaioru.retort;

import co.kaioru.retort.exception.CommandException;
import co.kaioru.retort.exception.CommandMiddlewareException;
import co.kaioru.retort.exception.CommandNotFoundException;

import java.util.List;
import java.util.Optional;
import java.util.Queue;
import java.util.Set;
import java.util.stream.Collectors;

public interface ICommand<I extends ICommandContext, O> extends ICommandExecutable<I, O> {

	String getName();

	String getDescription();

	Set<String> getAliases();

	Set<ICommandMiddleware<I>> getMiddlewares();

	Set<ICommand<I, O>> getCommands();

	default void registerAlias(String alias) {
		getAliases().add(alias);
	}

	default void deregisterAlias(String alias) {
		getAliases().remove(alias);
	}

	default void registerMiddleware(ICommandMiddleware<I> middleware) {
		getMiddlewares().add(middleware);
	}

	default void deregisterMiddleware(ICommandMiddleware<I> middleware) {
		getMiddlewares().remove(middleware);
	}

	default void registerCommand(ICommand<I, O> command) {
		getCommands().add(command);
	}

	default void deregisterCommand(ICommand<I, O> command) {
		getCommands().remove(command);
	}

	default List<ICommand> getCommand(String name) {
		return getCommands().stream()
			.filter(cmd -> cmd.getName().toLowerCase().startsWith(name)
				|| cmd.getAliases().stream().anyMatch(s -> s.toLowerCase().startsWith(name)))
			.collect(Collectors.toList());
	}

	default O process(I i) throws CommandException {
		Queue<String> args = i.getArgs();

		if (args.size() > 0) {
			String first = args.peek();
			Optional<ICommand> opt = getCommand(first)
				.stream()
				.findFirst();
			if (opt.isPresent()) {
				args.remove();
				return process(i);
			}
		}

		if (this instanceof ICommandRegistry)
			throw new CommandNotFoundException();

		if (getMiddlewares().stream()
			.map(m -> {
				try {
					return m.execute(i);
				} catch (CommandException e) {
					return false;
				}
			})
			.filter(b -> !b)
			.count() > 0)
			throw new CommandMiddlewareException();

		return execute(i);
	}

}
