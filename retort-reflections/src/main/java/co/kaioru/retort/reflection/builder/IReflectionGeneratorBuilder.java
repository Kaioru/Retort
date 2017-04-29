package co.kaioru.retort.reflection.builder;

import co.kaioru.retort.ICommandContext;
import co.kaioru.retort.reflection.IReflectionGenerator;
import co.kaioru.retort.reflection.IReflectionProvider;

public interface IReflectionGeneratorBuilder<I extends ICommandContext, O> {

    IReflectionGeneratorBuilder<I, O> withProvider(IReflectionProvider<I, O> provider);

    IReflectionGenerator<I, O> build();

}
