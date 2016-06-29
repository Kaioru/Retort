package co.kaioru.retort.util;

import co.kaioru.retort.CommandRegistry;
import co.kaioru.retort.command.ICommand;
import com.google.common.collect.Lists;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommandUtil {

	public static LinkedList<String> getArgsFromText(String text) {
		final LinkedList<String> args = new LinkedList<>();
		final Pattern p = Pattern.compile("([\"'])(?:(?=(\\\\?))\\2.)*?\\1|([^\\s]+)");
		final Matcher m = p.matcher(text);

		while (m.find()) {
			String s = m.group();

			if (s.startsWith("\'") || s.startsWith("\""))
				s = s.substring(1, s.length() - 1);
			args.add(s.toLowerCase());
		}
		return args;
	}

	public static Optional<Method> getMethod(Class classz, String name, int paramsCount) {
		return Arrays.asList(classz.getMethods())
				.stream()
				.filter(m -> m.getName().equals(name))
				.filter(m -> m.getParameterCount() == paramsCount)
				.findFirst();
	}

	public static void executeCommand(ICommand command, LinkedList<String> args, Object... params) throws Exception {
		if (args.size() > 0) {
			String first = args.removeFirst();
			Optional<ICommand> opt = command.getCommand(first)
					.stream()
					.findFirst();
			if (opt.isPresent()) {
				executeCommand(opt.get(), args, params);
				return;
			}
			args.addFirst(first);
		}

		if (!(command instanceof CommandRegistry)) {
			if (params.length == 0) command.execute(args);
			else {
				Optional<Method> opt = CommandUtil.getMethod(command.getClass(), "execute", params.length + 1);

				if (opt.isPresent()) {
					List<Object> list = Lists.newArrayList(args);
					Method method = opt.get();

					Arrays.stream(params).forEach(list::add);
					method.setAccessible(true);
					method.invoke(command, list.toArray());
				}
			}
		}
	}

}
