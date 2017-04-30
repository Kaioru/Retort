package co.kaioru.retort.impl

class CommandRegistry<I : CommandContext, O> : Command<I, O>(name = "registry") {
    override fun execute(input: I): O {
        TODO()
    }
}