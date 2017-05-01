package co.kaioru.retort.annotation.impl.adapter

import co.kaioru.retort.ICommand
import co.kaioru.retort.ICommandExecutable
import co.kaioru.retort.annotation.exceptions.AnnotationAdapterException
import co.kaioru.retort.annotation.impl.AnnotationGenerator
import co.kaioru.retort.annotation.type.Command
import co.kaioru.retort.impl.CommandContext
import co.kaioru.retort.inline.command
import java.lang.reflect.Method


class CommandAnnotationGenerator<I : CommandContext, O>(
        val inputClass: Class<I>,
        val outputClass: Class<O>
) : AnnotationGenerator<I, O>() {
    @Suppress("UNCHECKED_CAST")
    override fun generate(any: Any, method: Method): ICommand<I, O> {
        if (method.isAnnotationPresent(Command::class.java)) {
            if (method.parameters.size == 1
                    && method.parameters.first().type == inputClass
                    && method.returnType == outputClass) {
                val name: String = method.getAnnotation(Command::class.java).value

                return command(name) {
                    build(object : ICommandExecutable<I, O> {
                        override fun execute(input: I): O {
                            method.isAccessible = true
                            return method.invoke(any, input) as O
                        }
                    })
                }
            }
        }
        throw AnnotationAdapterException()
    }
}