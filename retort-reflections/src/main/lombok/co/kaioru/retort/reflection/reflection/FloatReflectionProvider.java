package co.kaioru.retort.reflection.reflection;

import co.kaioru.retort.ICommandContext;
import co.kaioru.retort.reflection.AbstractReflectionProvider;

public class FloatReflectionProvider extends AbstractReflectionProvider<ICommandContext, Float> {

    public FloatReflectionProvider() {
        super(Float.class);
    }

    @Override
    public Float provide(ICommandContext context) {
        return Float.valueOf(context.getArgs().remove());
    }

}
