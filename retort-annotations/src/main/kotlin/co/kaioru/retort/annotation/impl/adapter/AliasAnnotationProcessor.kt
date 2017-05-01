package co.kaioru.retort.annotation.impl.adapter

import co.kaioru.retort.ICommand
import co.kaioru.retort.annotation.Alias
import co.kaioru.retort.annotation.impl.AnnotationProcessor
import java.lang.reflect.Method

class AliasAnnotationProcessor<I, O> : AnnotationProcessor<I, O>() {
    override fun process(command: ICommand<I, O>, any: Any, method: Method) {
        method.getAnnotationsByType(Alias::class.java).forEach {
            command.registerAlias(it.value)
        }
    }
}