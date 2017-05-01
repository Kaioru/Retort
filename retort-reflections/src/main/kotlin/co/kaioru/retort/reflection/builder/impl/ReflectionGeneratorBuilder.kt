package co.kaioru.retort.reflection.builder.impl

import co.kaioru.retort.impl.CommandContext
import co.kaioru.retort.reflection.builder.IReflectionGeneratorBuilder
import co.kaioru.retort.reflection.impl.ReflectionGenerator

open class ReflectionGeneratorBuilder<I : CommandContext, O : Any> : ReflectionGenerator<I, O>(), IReflectionGeneratorBuilder<I, O>