package co.kaioru.retort.command;

import com.google.common.collect.Lists;

import java.util.List;

public abstract class Command implements ICommand {

	private final List<String> aliases;
	private final List<ICommand> commands;

	public Command() {
		this.aliases = Lists.newArrayList();
		this.commands = Lists.newArrayList();
	}

	@Override
	public List<String> getAliases() {
		return aliases;
	}

	@Override
	public List<ICommand> getCommands() {
		return commands;
	}

}
