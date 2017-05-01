package co.kaioru.retort.annotation

import co.kaioru.retort.ICommand

interface IAnnotationFactory<I, O> {
    val adapters: MutableCollection<IAnnotationAdapter<I, O>>

    fun registerAdapter(adapter: IAnnotationAdapter<I, O>) {
        this.adapters.add(adapter)
    }

    fun registerAdapters(adapters: Collection<IAnnotationAdapter<I, O>>) {
        this.adapters.addAll(adapters)
    }

    fun generate(input: Any): Collection<ICommand<I, O>>
}