package co.kaioru.retort.annotation

import co.kaioru.retort.ICommand
import java.lang.reflect.Method

interface IAnnotationProcessor<I, O> : IAnnotationAdapter<I, O> {
    fun process(command: ICommand<I, O>, any: Any, method: Method)
}