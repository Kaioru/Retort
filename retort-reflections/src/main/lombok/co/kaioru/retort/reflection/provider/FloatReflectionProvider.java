package co.kaioru.retort.reflection.provider;

import co.kaioru.retort.ICommandContext;
import co.kaioru.retort.reflection.AbstractReflectionProvider;

public class FloatReflectionProvider extends AbstractReflectionProvider<ICommandContext, Float> {

    public FloatReflectionProvider() {
        super(float.class);
    }

    @Override
    public Float provide(ICommandContext context) {
        return Float.valueOf(context.getArgs().remove());
    }

}
