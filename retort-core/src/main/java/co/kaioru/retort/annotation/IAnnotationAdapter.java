package co.kaioru.retort.annotation;

import co.kaioru.retort.ICommandContext;

public interface IAnnotationAdapter<I extends ICommandContext, O> {

    IAnnotationFactory getFactory();

}
