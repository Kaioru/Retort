package co.kaioru.retort;

import java.util.Queue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class CommandRegistry<I extends ICommandContext, O> extends AbstractCommand<I, O> {

	public O execute(I i, String text) throws Exception {
		Queue<String> args = i.getArgs();
		Pattern p = Pattern.compile("([\"'])(?:(?=(\\\\?))\\2.)*?\\1|([^\\s]+)");
		Matcher m = p.matcher(text);

		while (m.find()) {
			String s = m.group();

			if (s.startsWith("\'") || s.startsWith("\""))
				s = s.substring(1, s.length() - 1);
			args.add(s);
		}

		return execute(i);
	}

	@Override
	public O execute(I i) throws Exception {
		return process(i);
	}

}
