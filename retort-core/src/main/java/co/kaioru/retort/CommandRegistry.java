package co.kaioru.retort;

import co.kaioru.retort.command.Command;
import co.kaioru.retort.util.CommandUtil;
import com.google.common.collect.Lists;

import java.util.LinkedList;

public class CommandRegistry extends Command {

	private final String name;
	private final String desc;

	public CommandRegistry() {
		this("registry", "The default registry");
	}

	public CommandRegistry(String name, String desc) {
		this.name = name;
		this.desc = desc;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getDesc() {
		return desc;
	}

	@Override
	public void execute(LinkedList<String> args) throws Exception {
		execute(args, Lists.newArrayList());
	}

	public void execute(LinkedList<String> args, Object params) throws Exception {
		CommandUtil.executeCommand(this, args, params);
	}

}
