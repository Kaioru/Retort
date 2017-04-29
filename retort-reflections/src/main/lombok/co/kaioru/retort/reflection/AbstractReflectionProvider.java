package co.kaioru.retort.reflection;

import co.kaioru.retort.ICommandContext;
import lombok.Getter;

@Getter
public abstract class AbstractReflectionProvider<I extends ICommandContext, O> implements IReflectionProvider<I, O> {

    private final Class<O> type;

    public AbstractReflectionProvider(Class<O> type) {
        this.type = type;
    }

}
