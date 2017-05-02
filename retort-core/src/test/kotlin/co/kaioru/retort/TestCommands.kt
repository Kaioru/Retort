package co.kaioru.retort

import co.kaioru.retort.builder.impl.CommandBuilder
import co.kaioru.retort.builder.impl.MiddlewareBuilder
import co.kaioru.retort.impl.CommandContext
import co.kaioru.retort.impl.CommandRegistry

class TestCommandRegistry : CommandRegistry<CommandContext, Boolean>() {
    fun process(text: String): Boolean {
        return process(CommandContext(), text)
    }
}

class TestCommandBuilder(name: String) : CommandBuilder<CommandContext, Boolean>(name)

class TestMiddlewareBuilder : MiddlewareBuilder<CommandContext>()