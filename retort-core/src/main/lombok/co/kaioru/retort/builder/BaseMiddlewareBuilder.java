package co.kaioru.retort.builder;

import co.kaioru.retort.AbstractCommandMiddleware;
import co.kaioru.retort.ICommandContext;
import co.kaioru.retort.ICommandExecutable;
import co.kaioru.retort.ICommandMiddleware;
import co.kaioru.retort.exception.CommandException;
import co.kaioru.retort.exception.CommandNotBuiltException;

public class BaseMiddlewareBuilder<I extends ICommandContext> extends AbstractCommandMiddleware<I> implements IMiddlewareBuilder<I> {

    private ICommandExecutable<I, Boolean> executable;

    @Override
    public IMiddlewareBuilder<I> withName(String name) {
        this.setName(name);
        return this;
    }

    @Override
    public IMiddlewareBuilder<I> withDescription(String description) {
        this.setDescription(description);
        return this;
    }

    @Override
    public ICommandMiddleware<I> build(ICommandExecutable<I, Boolean> executable) {
        this.executable = executable;
        return this;
    }

    @Override
    public Boolean execute(I i) throws CommandException {
        if (executable == null)
            throw new CommandNotBuiltException();
        return executable.execute(i);
    }

}
