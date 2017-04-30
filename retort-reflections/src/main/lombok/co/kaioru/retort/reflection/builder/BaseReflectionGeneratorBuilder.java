package co.kaioru.retort.reflection.builder;

import co.kaioru.retort.ICommandContext;
import co.kaioru.retort.reflection.AbstractReflectionGenerator;
import co.kaioru.retort.reflection.IReflectionGenerator;
import co.kaioru.retort.reflection.IReflectionProvider;

public class BaseReflectionGeneratorBuilder<I extends ICommandContext, O> extends AbstractReflectionGenerator<I, O> implements IReflectionGeneratorBuilder<I, O> {

    public BaseReflectionGeneratorBuilder(Class<O> outputClass) {
        super(outputClass);
    }

    @Override
    public IReflectionGeneratorBuilder<I, O> withProvider(IReflectionProvider<I, ?> provider) {
        this.getProviders().put(provider.getType(), provider);
        return this;
    }

    @Override
    public IReflectionGenerator<I, O> build() {
        return this;
    }

}
