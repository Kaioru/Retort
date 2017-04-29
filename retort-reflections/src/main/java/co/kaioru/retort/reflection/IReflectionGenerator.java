package co.kaioru.retort.reflection;

import co.kaioru.retort.ICommandContext;
import co.kaioru.retort.annotation.IAnnotationGenerator;

import java.util.Map;

public interface IReflectionGenerator<I extends ICommandContext, O> extends IAnnotationGenerator<I, O> {

    Map<Class<?>, IReflectionProvider<I, ?>> getProviders();

}
