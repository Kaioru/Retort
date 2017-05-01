package co.kaioru.retort.annotation.builder.impl

import co.kaioru.retort.ICommand
import co.kaioru.retort.annotation.builder.IAnnotationFactoryBuilder
import co.kaioru.retort.annotation.impl.AnnotationFactory

open class AnnotationFactoryBuilder<I, O> : AnnotationFactory<I, O>(), IAnnotationFactoryBuilder<I, O> {
    val objects: MutableCollection<Any> = HashSet()

    override fun withObject(any: Any): IAnnotationFactoryBuilder<I, O> {
        objects.add(any)
        return this
    }

    override fun withObjects(any: Collection<Any>): IAnnotationFactoryBuilder<I, O> {
        objects.addAll(any)
        return this
    }

    override fun build(): Collection<ICommand<I, O>> {
        return objects
                .flatMap { generate(it) }
                .toSet()
    }
}