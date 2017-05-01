@file:JvmName("Adapters")
package co.kaioru.retort.annotation.impl.adapter

import co.kaioru.retort.ICommand
import co.kaioru.retort.ICommandExecutable
import co.kaioru.retort.annotation.*
import co.kaioru.retort.annotation.exceptions.AnnotationAdapterException
import co.kaioru.retort.annotation.impl.AnnotationGenerator
import co.kaioru.retort.annotation.impl.AnnotationProcessor
import co.kaioru.retort.builder.impl.CommandBuilder
import co.kaioru.retort.impl.CommandContext
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

                return CommandBuilder<I, O>(name)
                        .build(object : ICommandExecutable<I, O> {
                            override fun execute(input: I): O {
                                method.isAccessible = true
                                return method.invoke(any, input) as O
                            }
                        })
            }
        }
        throw AnnotationAdapterException()
    }
}

class AliasAnnotationProcessor<I, O> : AnnotationProcessor<I, O>() {
    override fun process(command: ICommand<I, O>, any: Any, method: Method) {
        method.getAnnotationsByType(Alias::class.java).forEach {
            command.registerAlias(it.value)
        }
    }
}

class DescriptionAnnotationProcessor<I, O> : AnnotationProcessor<I, O>() {
    override fun process(command: ICommand<I, O>, any: Any, method: Method) {
        if (method.isAnnotationPresent(Description::class.java))
            command.description = method.getAnnotation(Description::class.java).value
    }
}

fun <I : CommandContext, O> basicGenerators(input: Class<I>, output: Class<O>): Collection<IAnnotationGenerator<I, O>> {
    return listOf(
            CommandAnnotationGenerator(input, output)
    )
}

fun <I, O> basicProcessors(): Collection<IAnnotationProcessor<I, O>> {
    return listOf(
            AliasAnnotationProcessor(),
            DescriptionAnnotationProcessor()
    )
}