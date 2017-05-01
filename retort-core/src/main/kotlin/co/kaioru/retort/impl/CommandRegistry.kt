package co.kaioru.retort.impl

import co.kaioru.retort.exceptions.CommandRegistryException

class CommandRegistry<I : CommandContext, O> : Command<I, O>(name = "registry") {
    override fun execute(input: I): O {
        throw CommandRegistryException()
    }
}