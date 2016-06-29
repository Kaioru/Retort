package co.kaioru.retort;

import co.kaioru.retort.command.Command;
import co.kaioru.retort.util.builder.DefaultCommandBuilder;
import org.junit.Test;

import java.util.LinkedList;

import static co.kaioru.retort.util.CommandUtil.executeCommand;
import static co.kaioru.retort.util.CommandUtil.getArgsFromText;
import static org.junit.Assert.assertEquals;

public class CommandTest {

	private static final CommandRegistry registry = new CommandRegistry();

	@Test
	public void builder() throws Exception {
		registry
				.registerCommand(new DefaultCommandBuilder("built")
						.command(new DefaultCommandBuilder("inner")
								.build(args -> {
									assertEquals(args.removeFirst(), "first");
									assertEquals(args.removeFirst(), "second");
								}))
						.build(args -> {
							assertEquals(args.removeFirst(), "first");
							assertEquals(args.removeFirst(), "second");
						}));

		executeCommand(registry, getArgsFromText("built first second"));
		executeCommand(registry, getArgsFromText("built inner first second"));
	}

	@Test
	public void classes() throws Exception {
		Command built = new Command() {
			@Override
			public String getName() {
				return "class";
			}

			@Override
			public String getDesc() {
				return "desc";
			}

			@Override
			public void execute(LinkedList<String> args) throws Exception {
				assertEquals(args.removeFirst(), "first");
				assertEquals(args.removeFirst(), "second");
			}
		};

		built.registerCommand(new Command() {
			@Override
			public String getName() {
				return "inner";
			}

			@Override
			public String getDesc() {
				return "desc";
			}

			@Override
			public void execute(LinkedList<String> args) throws Exception {
				assertEquals(args.removeFirst(), "first");
				assertEquals(args.removeFirst(), "second");
			}
		});
		registry.registerCommand(built);

		executeCommand(registry, getArgsFromText("class first second"));
		executeCommand(registry, getArgsFromText("class inner first second"));
	}

	@Test
	public void argsWithSpace() throws Exception {
		registry.registerCommand(new DefaultCommandBuilder("long")
				.build(args -> {
					assertEquals(args.removeFirst(), "argument in quotes");
					assertEquals(args.removeFirst(), "argument in double quotes");
				}));

		executeCommand(registry, getArgsFromText("long 'argument in quotes' \"argument in double quotes\""));
	}

}
