package co.kaioru.retort.reflection.provider;

import co.kaioru.retort.ICommandContext;
import co.kaioru.retort.reflection.AbstractReflectionProvider;

public class BooleanReflectionProvider extends AbstractReflectionProvider<ICommandContext, Boolean> {

    public BooleanReflectionProvider() {
        super(boolean.class);
    }

    @Override
    public Boolean provide(ICommandContext context) {
        return Boolean.valueOf(context.getArgs().remove());
    }

}