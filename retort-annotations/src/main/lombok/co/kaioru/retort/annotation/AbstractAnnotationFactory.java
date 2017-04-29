package co.kaioru.retort.annotation;

import co.kaioru.retort.ICommand;
import co.kaioru.retort.ICommandContext;
import lombok.Getter;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

@Getter
public abstract class AbstractAnnotationFactory<I extends ICommandContext, O> implements IAnnotationFactory<I, O> {

    private final List<IAnnotationAdapter<I, O>> pipeline;

    public AbstractAnnotationFactory() {
        this.pipeline = new ArrayList<>();
    }

    @Override
    public List<ICommand<I, O>> generate(Object object) {
        List<ICommand<I, O>> commands = new ArrayList<>();

        for (Method method : object.getClass().getMethods()) {
            ICommand<I, O> command = null;
            for (IAnnotationAdapter<I, O> a : getPipeline()) {
                if (a instanceof IAnnotationGenerator)
                    command = ((IAnnotationGenerator<I, O>) a).generate(object, method);
                if (command != null) {
                    if (a instanceof IAnnotationProcessor)
                        ((IAnnotationProcessor<I, O>) a).process(command, object, method);
                    commands.add(command);
                }
            }
        }
        return commands;
    }

}
