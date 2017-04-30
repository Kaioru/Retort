package co.kaioru.retort.reflection.provider;

import co.kaioru.retort.ICommandContext;
import co.kaioru.retort.reflection.AbstractReflectionProvider;

public class IntegerReflectionProvider<I extends ICommandContext> extends AbstractReflectionProvider<I, Integer> {

    public IntegerReflectionProvider() {
        super(int.class);
    }

    @Override
    public Integer provide(ICommandContext context) {
        return Integer.valueOf(context.getArgs().remove());
    }

}
