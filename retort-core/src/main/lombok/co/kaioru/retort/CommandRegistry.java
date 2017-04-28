package co.kaioru.retort;

public class CommandRegistry<I extends ICommandContext, O> extends AbstractCommand<I, O> {

	@Override
	public O execute(I i) {
		return null;
	}

}
