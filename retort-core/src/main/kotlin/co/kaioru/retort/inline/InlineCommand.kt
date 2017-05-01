package co.kaioru.retort.inline

import co.kaioru.retort.ICommandExecutable
import co.kaioru.retort.builder.ICommandBuilder
import co.kaioru.retort.builder.impl.CommandBuilder
import co.kaioru.retort.impl.CommandContext

inline fun <I : CommandContext, O> command(
        name: String,
        body: ICommandBuilder<I, O>.() -> Unit
): ICommandBuilder<I, O> {
    return CommandBuilder(name)
}

inline fun <I : CommandContext, O> executable(
        crossinline body: I.() -> O
): ICommandExecutable<I, O> {
    return object : ICommandExecutable<I, O> {
        override fun execute(input: I): O {
            return body(input)
        }
    }
}