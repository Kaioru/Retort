package co.kaioru.retort.annotation.adapter;

import co.kaioru.retort.AbstractCommand;
import co.kaioru.retort.ICommand;
import co.kaioru.retort.ICommandContext;
import co.kaioru.retort.annotation.AbstractAnnotationGenerator;
import co.kaioru.retort.annotation.type.Command;
import co.kaioru.retort.exception.CommandException;
import co.kaioru.retort.exception.CommandReflectionException;
import lombok.Getter;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@Getter
public class CommandAnnotationGenerator<I extends ICommandContext, O> extends AbstractAnnotationGenerator<I, O> {

    private final Class<I> inputClass;
    private final Class<O> outputClass;

    public CommandAnnotationGenerator(Class<I> inputClass, Class<O> outputClass) {
        this.inputClass = inputClass;
        this.outputClass = outputClass;
    }

    @SuppressWarnings("unchecked")
    @Override
    public ICommand<I, O> generate(Object object, Method method) {
        ICommand<I, O> command = null;

        if (method.isAnnotationPresent(Command.class)) {
            if (method.getParameterCount() == 1
                    && method.getParameters()[0].getType().equals(getInputClass())
                    && method.getReturnType().equals(getOutputClass())) {
                String commandName = method.getAnnotation(Command.class).value();

                command = new AbstractCommand<I, O>(commandName) {

                    @Override
                    public O execute(I i) throws CommandException {
                        try {
                            method.setAccessible(true);
                            return (O) method.invoke(object, i);
                        } catch (InvocationTargetException | IllegalAccessException e) {
                            throw new CommandReflectionException();
                        }
                    }

                };
            }
        }
        return command;
    }

}
