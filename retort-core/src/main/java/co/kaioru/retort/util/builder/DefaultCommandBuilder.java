package co.kaioru.retort.util.builder;

import co.kaioru.retort.command.Command;
import co.kaioru.retort.command.ICommand;
import co.kaioru.retort.command.ICommandExecutable;

import java.util.LinkedList;
import java.util.List;

public class DefaultCommandBuilder extends CommandBuilder<DefaultCommandBuilder, Command, ICommandExecutable> {

	public DefaultCommandBuilder(String name) {
		super(name);
	}

	@Override
	public Command build(ICommandExecutable executable) {
		return new Command() {

			@Override
			public String getName() {
				return name;
			}

			@Override
			public String getDesc() {
				return desc;
			}

			@Override
			public List<String> getAliases() {
				return aliases;
			}

			@Override
			public List<ICommand> getCommands() {
				return commands;
			}

			@Override
			public void execute(LinkedList<String> args) throws Exception {
				executable.execute(args);
			}

		};
	}

}
