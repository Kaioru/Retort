package co.kaioru.retort

interface ICommandMiddleware<I> : ICommandExecutable<I, Boolean>