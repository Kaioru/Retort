package co.kaioru.retort;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class AbstractCommandMiddleware<I extends ICommandContext> implements ICommandMiddleware<I> {

	private String name;
	private String description;

}
