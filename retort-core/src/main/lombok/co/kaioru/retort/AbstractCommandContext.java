package co.kaioru.retort;

import lombok.Getter;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

@Getter
public abstract class AbstractCommandContext implements ICommandContext {

	private final Queue<String> args;

	protected AbstractCommandContext() {
		this.args = new LinkedBlockingQueue<>();
	}

}
