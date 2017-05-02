package co.kaioru.retort.annotatation

import co.kaioru.retort.annotation.builder.impl.AnnotationFactoryBuilder
import co.kaioru.retort.impl.CommandContext
import co.kaioru.retort.reflection.builder.impl.ReflectionGeneratorBuilder

class TestAnnotationFactoryBuilder : AnnotationFactoryBuilder<CommandContext, Boolean>()

class TestReflectionGeneratorBuilder : ReflectionGeneratorBuilder<CommandContext, Boolean>()