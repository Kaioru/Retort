package co.kaioru.retort.annotation;

import co.kaioru.retort.ICommand;
import co.kaioru.retort.ICommandContext;
import lombok.Getter;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

@Getter
public class BaseAnnotationFactory<I extends ICommandContext, O> implements IAnnotationFactory<I, O> {

    private final List<IAnnotationAdapter<I, O>> pipeline;

    public BaseAnnotationFactory() {
        this.pipeline = new ArrayList<>();
    }

    @Override
    public List<ICommand<I, O>> generate(Object object) {
        List<ICommand<I, O>> commands = new ArrayList<>();

        for (Method method : object.getClass().getMethods()) {
            List<ICommand<I, O>> curCommands = new ArrayList<>();
            for (IAnnotationAdapter<I, O> a : getPipeline()) {
                if (a instanceof IAnnotationGenerator) {
                    ICommand<I, O> command = ((IAnnotationGenerator<I, O>) a).generate(object, method);
                    if (command != null) curCommands.add(command);
                }
                if (a instanceof IAnnotationProcessor) {
                    curCommands.forEach(c ->
                            ((IAnnotationProcessor<I, O>) a).process(c, object, method));
                }
            }
            commands.addAll(curCommands);
        }
        return commands;
    }

}
