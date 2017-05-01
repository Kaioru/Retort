package co.kaioru.retort.annotation

import co.kaioru.retort.ICommand
import co.kaioru.retort.annotation.exceptions.AnnotationAdapterException
import java.lang.reflect.Method

interface IAnnotationGenerator<I, O> : IAnnotationAdapter<I, O> {
    @Throws(AnnotationAdapterException::class)
    fun generate(any: Any, method: Method): ICommand<I, O>
}