package co.kaioru.retort.annotation;

import co.kaioru.retort.ICommandContext;
import lombok.Getter;

@Getter
public abstract class AbstractAnnotationAdapter<I extends ICommandContext, O> implements IAnnotationAdapter<I, O> {

    //

}
