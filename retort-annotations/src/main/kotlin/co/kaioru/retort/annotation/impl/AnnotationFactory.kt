package co.kaioru.retort.annotation.impl

import co.kaioru.retort.ICommand
import co.kaioru.retort.annotation.IAnnotationAdapter
import co.kaioru.retort.annotation.IAnnotationFactory
import co.kaioru.retort.annotation.IAnnotationGenerator
import co.kaioru.retort.annotation.IAnnotationProcessor

class AnnotationFactory<I, O> : IAnnotationFactory<I, O> {
    override val adapters: MutableCollection<IAnnotationAdapter<I, O>> = HashSet()

    override fun generate(input: Any): Collection<ICommand<I, O>> {
        val commands: MutableSet<ICommand<I, O>> = HashSet()

        input.javaClass.methods.forEach { method ->
            val currentCommands: MutableSet<ICommand<I, O>> = HashSet()

            adapters.forEach { adapter ->
                if (adapter is IAnnotationGenerator)
                    currentCommands.add(adapter.generate(input, method))
                if (adapter is IAnnotationProcessor)
                    currentCommands.forEach { adapter.process(it, input, method) }
            }
            commands.addAll(currentCommands)
        }
        return commands
    }
}