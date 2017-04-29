package co.kaioru.retort.reflection;

import co.kaioru.retort.ICommandContext;
import co.kaioru.retort.annotation.IAnnotationGenerator;

import java.util.Map;

public interface IReflectionGenerator<I extends ICommandContext, O> extends IAnnotationGenerator<I, O> {

    Map<Class<?>, IReflectionProvider<I, ?>> getProviders();

    default void registerProvider(IReflectionProvider<I, ?> provider) {
        getProviders().put(provider.getType(), provider);
    }

    default void deregisterProvider(IReflectionProvider<I, ?> provider) {
        getProviders().remove(provider.getType());
    }

}
