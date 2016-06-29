package co.kaioru.retort.util.builder;

import co.kaioru.retort.command.ICommand;
import co.kaioru.retort.command.ICommandExecutable;
import com.google.common.collect.Lists;

import java.util.List;

public abstract class CommandBuilder<T extends CommandBuilder, C extends ICommand, E extends ICommandExecutable> {

	protected final String name;
	protected String desc;
	protected List<String> aliases;
	protected List<ICommand> commands;

	public CommandBuilder(String name) {
		this.name = name;
		this.desc = "No description";
		this.aliases = Lists.newArrayList();
		this.commands = Lists.newArrayList();
	}

	public T desc(String desc) {
		this.desc = desc;
		return (T) this;
	}

	public T alias(String alias) {
		this.aliases.add(alias);
		return (T) this;
	}

	public T command(ICommand command) {
		this.commands.add(command);
		return (T) this;
	}

	public abstract C build(E executable);

}
