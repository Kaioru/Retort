package co.kaioru.retort.annotation.impl.adapter

import co.kaioru.retort.ICommand
import co.kaioru.retort.annotation.Description
import co.kaioru.retort.annotation.impl.AnnotationProcessor
import java.lang.reflect.Method

class DescriptionAnnotationProcessor<I, O> : AnnotationProcessor<I, O>() {
    override fun process(command: ICommand<I, O>, any: Any, method: Method) {
        if (method.isAnnotationPresent(Description::class.java))
            command.description = method.getAnnotation(Description::class.java).value
    }
}