package co.kaioru.retort.annotation;

import co.kaioru.retort.ICommand;
import co.kaioru.retort.ICommandContext;

import java.util.List;

public interface IAnnotationFactory<I extends ICommandContext, O> {

    List<IAnnotationAdapter<I, O>> getPipeline();

    default void registerAdapter(IAnnotationAdapter<I, O> adapter) {
        getPipeline().add(adapter);
    }

    default void deregisterAdapter(IAnnotationAdapter<I, O> adapter) {
        getPipeline().remove(adapter);
    }

    List<ICommand<I, O>> generate(Object object);

}
