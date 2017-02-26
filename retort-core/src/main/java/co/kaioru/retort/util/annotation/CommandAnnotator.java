package co.kaioru.retort.util.annotation;

import co.kaioru.retort.command.ICommand;
import com.google.common.collect.Lists;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Optional;

public abstract class CommandAnnotator<T extends CommandAnnotator, C extends ICommand> {

	public T registerAnnotations(ICommand command, Object object) throws Exception {
		List<Method> independent = Lists.newArrayList(),
			dependent = Lists.newArrayList();

		for (Method method : object.getClass().getMethods()) {
			if (method.isAnnotationPresent(AnnotatedCommand.class)) {
				if (method.getAnnotation(AnnotatedCommand.class).commands().length == 0)
					independent.add(method);
				else dependent.add(method);
			}
		}

		List<ICommand> dependencies = Lists.newArrayList();

		independent.forEach(m -> {
			generateCommand(object, m, dependencies).ifPresent(c -> {
				dependencies.add(c);
			});
		});
		dependent.forEach(m ->
			generateCommand(object, m, dependencies).ifPresent(command::registerCommand));
		return (T) this;
	}

	protected abstract Optional<C> generateCommand(Object object, Method method, List<ICommand> dependencies);

	public T registerReferences(ICommand command, Object object) throws Exception {
		for (Method method : object.getClass().getMethods()) {
			if (method.isAnnotationPresent(ReferencedCommand.class)) {
				method.setAccessible(true);
				command.registerCommand((ICommand) method.invoke(object));
			}
		}
		return (T) this;
	}

}
