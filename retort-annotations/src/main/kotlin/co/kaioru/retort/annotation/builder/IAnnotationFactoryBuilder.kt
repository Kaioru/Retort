package co.kaioru.retort.annotation.builder

import co.kaioru.retort.ICommand
import co.kaioru.retort.annotation.IAnnotationAdapter
import co.kaioru.retort.annotation.IAnnotationFactory

interface IAnnotationFactoryBuilder<I, O> : IAnnotationFactory<I, O> {
    fun withAdapter(adapter: IAnnotationAdapter<I, O>): IAnnotationFactoryBuilder<I, O> {
        registerAdapter(adapter)
        return this
    }

    fun withAdapters(adapter: Collection<IAnnotationAdapter<I, O>>): IAnnotationFactoryBuilder<I, O> {
        registerAdapters(adapters)
        return this
    }

    fun withObject(any: Any)

    fun withObjects(any: Collection<Any>)

    fun build(): Collection<ICommand<I, O>>
}