package co.kaioru.retort.reflection.reflection;

import co.kaioru.retort.ICommandContext;
import co.kaioru.retort.reflection.AbstractReflectionProvider;

public class DoubleReflectionProvider extends AbstractReflectionProvider<ICommandContext, Double> {

    public DoubleReflectionProvider() {
        super(Double.class);
    }

    @Override
    public Double provide(ICommandContext context) {
        return Double.valueOf(context.getArgs().remove());
    }

}
