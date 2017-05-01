package co.kaioru.retort.impl

import java.util.*
import java.util.concurrent.LinkedBlockingQueue

open class CommandContext {
    val args: Queue<String> = LinkedBlockingQueue()
}