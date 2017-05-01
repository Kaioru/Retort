package co.kaioru.retort.reflection

import co.kaioru.retort.annotation.IAnnotationGenerator

interface IReflectionGenerator<I, O : Any> : IAnnotationGenerator<I, O> {
    val providers: MutableMap<Class<*>, IReflectionProvider<I, *>>

    fun registerProvider(provider: IReflectionProvider<I, *>) {
        this.providers.put(provider.type, provider)
    }

    fun registerProviders(providers: Collection<IReflectionProvider<I, *>>) {
        providers.forEach { registerProvider(it) }
    }
}