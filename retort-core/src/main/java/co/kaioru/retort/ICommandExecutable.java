package co.kaioru.retort;

public interface ICommandExecutable<I extends ICommandContext, O> {

	O execute(I i) throws Exception;

}
