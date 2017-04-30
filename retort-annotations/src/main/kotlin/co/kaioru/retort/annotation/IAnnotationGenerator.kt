package co.kaioru.retort.annotation

import co.kaioru.retort.ICommand
import java.lang.reflect.Method

interface IAnnotationGenerator<I, O> : IAnnotationAdapter<I, O> {
    fun generate(any: Any, method: Method): ICommand<I, O>
}