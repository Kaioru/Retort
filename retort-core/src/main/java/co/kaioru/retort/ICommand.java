package co.kaioru.retort;

import java.util.Set;

public interface ICommand<I extends ICommandContext, O> extends ICommandExecutable<I, O> {

	String getName();

	String getDescription();

	Set<String> getAliases();

	Set<ICommand<I, O>> getCommands();

	default void registerAlias(String alias) {
		getAliases().add(alias);
	}

	default void deregisterAlias(String alias) {
		getAliases().remove(alias);
	}

	default void registerCommand(ICommand<I, O> command) {
		getCommands().add(command);
	}

	default void deregisterCommand(ICommand<I, O> command) {
		getCommands().remove(command);
	}

}
