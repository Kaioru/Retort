package co.kaioru.retort.annotation.adapter;

import co.kaioru.retort.ICommand;
import co.kaioru.retort.ICommandContext;
import co.kaioru.retort.annotation.AbstractAnnotationGenerator;
import co.kaioru.retort.annotation.type.Reference;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReferenceAnnotationGenerator<I extends ICommandContext, O> extends AbstractAnnotationGenerator<I, O> {

    @SuppressWarnings("unchecked")
    @Override
    public ICommand<I, O> generate(Object object, Method method) {
        ICommand<I, O> command = null;

        if (method.isAnnotationPresent(Reference.class)) {
            try {
                command = (ICommand<I, O>) method.invoke(object);
            } catch (IllegalAccessException | InvocationTargetException e) {
                return null;
            }
        }
        return command;
    }

}
