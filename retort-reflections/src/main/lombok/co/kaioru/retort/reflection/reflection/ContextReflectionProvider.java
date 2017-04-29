package co.kaioru.retort.reflection.reflection;

import co.kaioru.retort.ICommandContext;
import co.kaioru.retort.reflection.AbstractReflectionProvider;

public class ContextReflectionProvider<I extends ICommandContext> extends AbstractReflectionProvider<I, I> {

    public ContextReflectionProvider(Class<I> inputClass) {
        super(inputClass);
    }

    @Override
    public I provide(I context) {
        return context;
    }

}