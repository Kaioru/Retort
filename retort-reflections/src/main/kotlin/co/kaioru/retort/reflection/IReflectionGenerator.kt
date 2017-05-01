package co.kaioru.retort.reflection

import co.kaioru.retort.annotation.IAnnotationGenerator

interface IReflectionGenerator<I, O : Any> : IAnnotationGenerator<I, O> {
    val providers: MutableMap<Class<O>, IReflectionProvider<I, Any>>
}