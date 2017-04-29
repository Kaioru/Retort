package co.kaioru.retort.reflection;

import co.kaioru.retort.ICommandContext;

public interface IReflectionProvider<I extends ICommandContext, O> {

    Class<O> getType();

    O provide(I i);

}
