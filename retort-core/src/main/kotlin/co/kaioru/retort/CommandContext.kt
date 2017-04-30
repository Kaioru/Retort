package co.kaioru.retort

import java.util.*
import java.util.concurrent.LinkedBlockingQueue

open class CommandContext {
    val args: Queue<String> = LinkedBlockingQueue()
}