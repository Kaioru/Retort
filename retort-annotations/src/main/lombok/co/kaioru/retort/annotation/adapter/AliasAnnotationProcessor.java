package co.kaioru.retort.annotation.adapter;

import co.kaioru.retort.ICommand;
import co.kaioru.retort.annotation.AbstractAnnotationProcessor;
import co.kaioru.retort.annotation.Alias;
import co.kaioru.retort.annotation.Aliases;

import java.lang.reflect.Method;

public class AliasAnnotationProcessor extends AbstractAnnotationProcessor {

    @Override
    public void process(ICommand command, Object object, Method method) {
        if (method.isAnnotationPresent(Aliases.class)) {
            for (Alias alias : method.getAnnotation(Aliases.class).value())
                command.registerAlias(alias.value());
        }
    }

}
