package co.kaioru.retort.annotation.adapter;

import co.kaioru.retort.ICommand;
import co.kaioru.retort.annotation.AbstractAnnotationProcessor;
import co.kaioru.retort.annotation.Description;

import java.lang.reflect.Method;

public class DescriptionAnnotationProcessor extends AbstractAnnotationProcessor {

    @Override
    public void process(ICommand command, Object object, Method method) {
        if (method.isAnnotationPresent(Description.class))
            command.setDescription(method.getAnnotation(Description.class).value());
    }

}
