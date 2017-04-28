package co.kaioru.retort.builder;

import co.kaioru.retort.ICommandContext;
import co.kaioru.retort.annotation.IAnnotationFactory;

import java.lang.reflect.Method;

public interface IAnnotationFactoryBuilder<I extends ICommandContext, O> {

    IAnnotationFactoryBuilder<I, O> with(Object object);

    IAnnotationFactoryBuilder<I, O> with(Object object, Method method);

    IAnnotationFactory<I, O> build();

}
