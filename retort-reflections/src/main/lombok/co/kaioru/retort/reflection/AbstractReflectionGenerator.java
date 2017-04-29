package co.kaioru.retort.reflection;

import co.kaioru.retort.AbstractCommand;
import co.kaioru.retort.ICommand;
import co.kaioru.retort.ICommandContext;
import co.kaioru.retort.annotation.type.Command;
import co.kaioru.retort.exception.CommandException;
import co.kaioru.retort.exception.CommandInvalidSyntaxException;
import co.kaioru.retort.reflection.exception.CommandProviderException;
import co.kaioru.retort.exception.CommandReflectionException;
import co.kaioru.retort.reflection.type.Optional;
import lombok.Getter;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
public abstract class AbstractReflectionGenerator<I extends ICommandContext, O> implements IReflectionGenerator<I, O> {

    private final Map<Class<?>, IReflectionProvider<I, ?>> providers;
    private final Class<O> outputClass;

    public AbstractReflectionGenerator(Class<O> outputClass) {
        this.outputClass = outputClass;
        this.providers = new HashMap<>();
    }

    @SuppressWarnings("unchecked")
    @Override
    public ICommand<I, O> generate(Object object, Method method) {
        ICommand<I, O> command = null;

        if (method.isAnnotationPresent(Command.class)) {
            if (method.getReturnType().equals(getOutputClass())) {
                String commandName = method.getAnnotation(Command.class).value();

                command = new AbstractCommand<I, O>(commandName) {

                    @Override
                    public O execute(I i) throws CommandException {
                        List<Object> params = new ArrayList<>();

                        for (Parameter param : method.getParameters()) {
                            IReflectionProvider<I, ?> provider = getProviders().get(param.getType());

                            if (provider != null) {
                                try {
                                    params.add(provider.provide(i));
                                } catch (Exception e) {
                                    if (!param.isAnnotationPresent(Optional.class))
                                        throw new CommandInvalidSyntaxException();
                                    else params.add(null);
                                }
                            } else
                                throw new CommandProviderException();
                        }

                        try {
                            return (O) method.invoke(object, params.toArray());
                        } catch (IllegalAccessException | InvocationTargetException e) {
                            throw new CommandReflectionException();
                        }
                    }

                };
            }
        }
        return command;
    }

}
