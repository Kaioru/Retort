package co.kaioru.retort.annotation.builder;

import co.kaioru.retort.ICommand;
import co.kaioru.retort.ICommandContext;
import co.kaioru.retort.annotation.IAnnotationAdapter;

import java.util.Collection;

public interface IAnnotationFactoryBuilder<I extends ICommandContext, O> {

    IAnnotationFactoryBuilder<I, O> withAdapter(IAnnotationAdapter<I, O> adapter);

    IAnnotationFactoryBuilder<I, O> withObject(Object object);

    Collection<ICommand<I, O>> build();

}
