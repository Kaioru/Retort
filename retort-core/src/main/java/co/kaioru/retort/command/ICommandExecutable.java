package co.kaioru.retort.command;

import java.util.LinkedList;

@FunctionalInterface
public interface ICommandExecutable {

	void execute(LinkedList<String> args) throws Exception;

}
