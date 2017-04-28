package co.kaioru.retort.annotation;

import co.kaioru.retort.ICommand;
import co.kaioru.retort.ICommandContext;
import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Getter
@Setter
public class AbstractAnnotationFactory<I extends ICommandContext, O> extends AbstractAnnotationGenerator<I, O> implements IAnnotationFactory<I, O> {

    private final IAnnotationGenerator<I, O> generator;
    private final List<IAnnotationProcessor<I, O>> processors;

    public AbstractAnnotationFactory(IAnnotationGenerator generator) {
        this.generator = generator;
        this.processors = new ArrayList<>();
    }

    @Override
    public List<ICommand<I, O>> process(Object object) {
        return Arrays.stream(object.getClass().getMethods())
                .map(this::generate)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    @Override
    public ICommand<I, O> generate(Method method) {
        ICommand<I, O> command = getGenerator().generate(method);
        if (command != null) getProcessors().forEach(p -> p.process(command));
        return command;
    }

}
