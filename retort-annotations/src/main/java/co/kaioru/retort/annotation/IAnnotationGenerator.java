package co.kaioru.retort.annotation;

import co.kaioru.retort.ICommand;
import co.kaioru.retort.ICommandContext;

import java.lang.reflect.Method;

public interface IAnnotationGenerator<I extends ICommandContext, O> extends IAnnotationAdapter<I, O> {

    ICommand<I, O> generate(Object object, Method method);

}
