package co.kaioru.retort.exceptions

open class CommandException : Exception()

class CommandMiddlewareException : CommandException()

class CommandNotBuiltException : CommandException()