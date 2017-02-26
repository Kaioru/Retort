package co.kaioru.retort.command;

import java.util.List;
import java.util.stream.Collectors;

public interface ICommand extends ICommandExecutable {

	String getName();

	String getDesc();

	List<String> getAliases();

	List<ICommand> getCommands();

	default List<ICommand> getCommand(String name) {
		return getCommands().stream()
			.filter(cmd -> cmd.getName().toLowerCase().startsWith(name)
				|| cmd.getAliases().stream().anyMatch(s -> s.toLowerCase().startsWith(name)))
			.collect(Collectors.toList());
	}

	default ICommand registerCommand(ICommand command) {
		getCommands().add(command);
		return this;
	}

	default ICommand unregisterCommand(ICommand command) {
		getCommands().remove(command);
		return this;
	}

}
