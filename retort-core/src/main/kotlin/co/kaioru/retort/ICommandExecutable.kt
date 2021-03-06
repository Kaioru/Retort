package co.kaioru.retort

import co.kaioru.retort.exceptions.CommandException

@FunctionalInterface
interface ICommandExecutable<in I, out O> {

    @Throws(CommandException::class)
    fun execute(input: I): O

}