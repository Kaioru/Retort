package co.kaioru.retort.annotation.impl.adapter

import co.kaioru.retort.ICommand
import co.kaioru.retort.annotation.impl.AnnotationGenerator
import co.kaioru.retort.impl.CommandContext
import java.lang.reflect.Method


class CommandAnnotationGenerator<I : CommandContext, O>(
        val inputClass: Class<I>,
        val outputClass: Class<O>
) : AnnotationGenerator<I, O>() {
    override fun generate(any: Any, method: Method): ICommand<I, O> {
        TODO()
    }
}