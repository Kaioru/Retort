package co.kaioru.retort.builder;

import co.kaioru.retort.*;
import co.kaioru.retort.exception.CommandException;
import co.kaioru.retort.exception.CommandNotBuiltException;

import java.util.Collection;

public class BaseCommandBuilder<I extends ICommandContext, O> extends AbstractCommand<I, O> implements ICommandBuilder<I, O> {

    private ICommandExecutable<I, O> executable;

    public BaseCommandBuilder(String name) {
        super(name);
    }

    @Override
    public ICommandBuilder<I, O> withDescription(String description) {
        this.setDescription(description);
        return this;
    }

    @Override
    public ICommandBuilder<I, O> withAlias(String alias) {
        this.getAliases().add(alias);
        return this;
    }

    @Override
    public ICommandBuilder<I, O> withAliases(Collection<? extends String> aliases) {
        this.getAliases().addAll(aliases);
        return this;
    }

    @Override
    public ICommandBuilder<I, O> withMiddleware(ICommandMiddleware<I> middleware) {
        this.getMiddlewares().add(middleware);
        return this;
    }

    @Override
    public ICommandBuilder<I, O> withMiddlewares(Collection<? extends ICommandMiddleware<I>> middleware) {
        this.getMiddlewares().addAll(middleware);
        return this;
    }

    @Override
    public ICommandBuilder<I, O> withCommand(ICommand<I, O> command) {
        this.getCommands().add(command);
        return this;
    }

    @Override
    public ICommandBuilder<I, O> withCommands(Collection<? extends ICommand<I, O>> commands) {
        this.getCommands().addAll(commands);
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