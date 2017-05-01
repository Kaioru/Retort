package co.kaioru.retort.reflection.builder

import co.kaioru.retort.reflection.IReflectionGenerator
import co.kaioru.retort.reflection.IReflectionProvider

interface IReflectionGeneratorBuilder<I, O : Any> : IReflectionGenerator<I, O> {
    fun withProvider(provider: IReflectionProvider<I, *>): IReflectionGeneratorBuilder<I, O> {
        registerProvider(provider)
        return this
    }

    fun withProviders(providers: Collection<IReflectionProvider<I, *>>): IReflectionGeneratorBuilder<I, O> {
        registerProviders(providers)
        return this
    }

    fun build(): IReflectionGenerator<I, O> {
        return this
    }
}