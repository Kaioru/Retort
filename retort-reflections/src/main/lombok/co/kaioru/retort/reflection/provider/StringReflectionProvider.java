package co.kaioru.retort.reflection.provider;

import co.kaioru.retort.ICommandContext;
import co.kaioru.retort.reflection.AbstractReflectionProvider;

public class StringReflectionProvider<I extends ICommandContext> extends AbstractReflectionProvider<I, String> {

    public StringReflectionProvider() {
        super(String.class);
    }

    @Override
    public String provide(ICommandContext context) {
        return context.getArgs().remove();
    }

}
