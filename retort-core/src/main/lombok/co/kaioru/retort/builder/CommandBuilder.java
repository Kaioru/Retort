package co.kaioru.retort.builder;

import co.kaioru.retort.*;
import co.kaioru.retort.exception.CommandException;
import co.kaioru.retort.exception.CommandNotBuiltException;

public class CommandBuilder<I extends ICommandContext, O> extends AbstractCommand<I, O> implements ICommandBuilder<I, O> {

	private ICommandExecutable<I, O> executable;

	public CommandBuilder(String name) {
		super(name);
	}

	@Override
	public ICommandBuilder withDescription(String description) {
		this.setDescription(description);
		return this;
	}

	@Override
	public ICommandBuilder withAlias(String alias) {
		this.getAliases().add(alias);
		return this;
	}

	@Override
	public ICommandBuilder withMiddleware(ICommandMiddleware<I> middleware) {
		this.getMiddlewares().add(middleware);
		return this;
	}

	@Override
	public ICommandBuilder withCommand(ICommand<I, O> command) {
		this.getCommands().add(command);
		return this;
	}

	@Override
	public ICommand<I, O> build(ICommandExecutable<I, O> executable) {
		this.executable = executable;
		return this;
	}

	@Override
	public O execute(I i) throws CommandException {
		if (executable == null)
			throw new CommandNotBuiltException();
		return executable.execute(i);
	}

}
