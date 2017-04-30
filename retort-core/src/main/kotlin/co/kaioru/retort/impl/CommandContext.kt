package co.kaioru.retort.impl

import java.util.*
import java.util.concurrent.LinkedBlockingQueue

open class CommandContext {
    val args: java.util.Queue<String> = java.util.concurrent.LinkedBlockingQueue()
}