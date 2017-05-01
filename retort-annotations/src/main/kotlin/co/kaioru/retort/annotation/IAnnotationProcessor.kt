package co.kaioru.retort.annotation

import co.kaioru.retort.ICommand
import co.kaioru.retort.annotation.exceptions.AnnotationAdapterException
import java.lang.reflect.Method

interface IAnnotationProcessor<I, O> : IAnnotationAdapter<I, O> {
    @Throws(AnnotationAdapterException::class)
    fun process(command: ICommand<I, O>, any: Any, method: Method)
}