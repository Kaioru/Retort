package co.kaioru.retort.annotation;

import co.kaioru.retort.ICommand;
import co.kaioru.retort.ICommandContext;

public interface IAnnotationProcessor<I extends ICommandContext, O> {

    ICommand<I, O> process(ICommand<I, O> command);

}
