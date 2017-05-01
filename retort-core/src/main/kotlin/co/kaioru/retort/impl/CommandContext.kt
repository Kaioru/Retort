package co.kaioru.retort.impl

open class CommandContext {
    val args: java.util.Queue<String> = java.util.concurrent.LinkedBlockingQueue()
}