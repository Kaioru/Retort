package co.kaioru.retort.util.annotation;

import co.kaioru.retort.command.Command;
import co.kaioru.retort.command.ICommand;
import com.google.common.collect.Lists;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class DefaultCommandAnnotator extends CommandAnnotator<DefaultCommandAnnotator, Command> {

	@Override
	protected Optional<Command> generateCommand(Object object, Method method, List<ICommand> dependencies) {
		AnnotatedCommand annotation = method.getAnnotation(AnnotatedCommand.class);

		List<String> aliases = Arrays.asList(annotation.aliases());
		List<ICommand> commands = Lists.newArrayList();

		Arrays.asList(annotation.commands())
			.forEach(s -> {
				dependencies.stream()
					.filter(c -> c.getName().equals(s))
					.findFirst()
					.ifPresent(commands::add);
			});

		return Optional.of(new Command() {

			@Override
			public String getName() {
				return annotation.name();
			}

			@Override
			public String getDesc() {
				return annotation.desc();
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
				method.setAccessible(true);
				method.invoke(object, args);
			}

		});
	}

}
