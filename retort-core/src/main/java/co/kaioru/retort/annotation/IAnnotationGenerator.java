package co.kaioru.retort.annotation;

import co.kaioru.retort.ICommand;
import co.kaioru.retort.ICommandContext;

import java.lang.reflect.Method;

public interface IAnnotationGenerator<I extends ICommandContext, O> {

    ICommand<I, O> generate(Method method);

}
