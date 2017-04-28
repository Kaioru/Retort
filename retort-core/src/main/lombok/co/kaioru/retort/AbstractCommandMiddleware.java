package co.kaioru.retort;

import lombok.Getter;

@Getter
public abstract class AbstractCommandMiddleware<I extends ICommandContext> implements ICommandMiddleware<I> {

	private String name;
	private String description;
	
}
