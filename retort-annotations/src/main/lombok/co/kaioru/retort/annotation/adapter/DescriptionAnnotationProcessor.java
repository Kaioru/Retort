package co.kaioru.retort.annotation.adapter;

import co.kaioru.retort.ICommand;
import co.kaioru.retort.ICommandContext;
import co.kaioru.retort.annotation.AbstractAnnotationProcessor;
import co.kaioru.retort.annotation.type.Description;

import java.lang.reflect.Method;

public class DescriptionAnnotationProcessor<I extends ICommandContext, O> extends AbstractAnnotationProcessor<I, O> {

    @Override
    public void process(ICommand command, Object object, Method method) {
        if (method.isAnnotationPresent(Description.class))
            command.setDescription(method.getAnnotation(Description.class).value());
    }

}
