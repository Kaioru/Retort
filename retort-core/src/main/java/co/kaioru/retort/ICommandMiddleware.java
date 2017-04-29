package co.kaioru.retort;

public interface ICommandMiddleware<I extends ICommandContext> extends ICommandExecutable<I, Boolean> {

    String getName();

    String getDescription();

}
