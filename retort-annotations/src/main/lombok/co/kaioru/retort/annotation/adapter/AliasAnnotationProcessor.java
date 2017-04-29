package co.kaioru.retort.annotation.adapter;

import co.kaioru.retort.ICommand;
import co.kaioru.retort.ICommandContext;
import co.kaioru.retort.annotation.AbstractAnnotationProcessor;
import co.kaioru.retort.annotation.type.Alias;

import java.lang.reflect.Method;

public class AliasAnnotationProcessor<I extends ICommandContext, O> extends AbstractAnnotationProcessor<I, O> {

    @Override
    public void process(ICommand command, Object object, Method method) {
        for (Alias alias : method.getAnnotationsByType(Alias.class))
            command.registerAlias(alias.value());
    }

}
