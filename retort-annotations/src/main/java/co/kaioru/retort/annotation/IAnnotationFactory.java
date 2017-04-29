package co.kaioru.retort.annotation;

import co.kaioru.retort.ICommand;
import co.kaioru.retort.ICommandContext;

import java.util.List;

public interface IAnnotationFactory<I extends ICommandContext, O> {

    List<IAnnotationAdapter<I, O>> getPipeline();

    List<ICommand<I, O>> generate(Object object);

}
