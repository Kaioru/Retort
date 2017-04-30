package co.kaioru.retort

@FunctionalInterface
open interface ICommandExecutable<in I, out O> {

    fun execute(input: I): O

}