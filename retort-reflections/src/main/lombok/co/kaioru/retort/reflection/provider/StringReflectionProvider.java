package co.kaioru.retort.reflection.provider;

import co.kaioru.retort.ICommandContext;
import co.kaioru.retort.reflection.AbstractReflectionProvider;

public class StringReflectionProvider extends AbstractReflectionProvider<ICommandContext, String> {

    public StringReflectionProvider() {
        super(String.class);
    }

    @Override
    public String provide(ICommandContext context) {
        return context.getArgs().remove();
    }

}
