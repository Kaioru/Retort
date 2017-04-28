package co.kaioru.retort.annotation;

import co.kaioru.retort.ICommand;
import co.kaioru.retort.ICommandContext;

import java.util.List;

public interface IAnnotationFactory<I extends ICommandContext, O> extends IAnnotationGenerator<I, O> {

    IAnnotationGenerator<I, O> getGenerator();

    List<IAnnotationProcessor<I, O>> getProcessors();

    List<ICommand<I, O>> process(Object object);

}
