package co.kaioru.retort.annotation;

import co.kaioru.retort.ICommand;
import co.kaioru.retort.ICommandContext;

import java.lang.reflect.Method;

public interface IAnnotationProcessor<I extends ICommandContext, O> extends IAnnotationAdapter<I, O> {

    void process(ICommand<I, O> command, Object object, Method method);

}
