package co.kaioru.retort;

import co.kaioru.retort.exception.CommandException;
import co.kaioru.retort.exception.CommandMiddlewareException;
import co.kaioru.retort.exception.CommandNotFoundException;
import lombok.Getter;
import lombok.Setter;

import java.util.*;
import java.util.stream.Collectors;

@Getter
@Setter
public abstract class AbstractCommand<I extends ICommandContext, O> implements ICommand<I, O> {

    private final String name;
    private final Set<String> aliases;
    private final Set<ICommandMiddleware<I>> middlewares;
    private final Set<ICommand<I, O>> commands;
    private String description;

    public AbstractCommand(String name) {
        this.name = name;
        this.aliases = new HashSet<>();
        this.middlewares = new HashSet<>();
        this.commands = new HashSet<>();
    }

    @Override
    public List<ICommand<I, O>> getCommand(String name) {
        return getCommands().stream()
                .filter(cmd -> cmd.getName().toLowerCase().startsWith(name)
                        || cmd.getAliases().stream().anyMatch(s -> s.toLowerCase().startsWith(name)))
                .collect(Collectors.toList());
    }

    @Override
    public O process(I i) throws CommandException {
        LinkedList<String> args = i.getArgs();

        if (args.size() > 0) {
            String first = args.peek();
            Optional<ICommand<I, O>> opt = getCommand(first)
                    .stream()
                    .findFirst();
            if (opt.isPresent()) {
                args.remove();
                return (O) opt.get().process(i);
            }
        }

        if (this instanceof ICommandRegistry)
            throw new CommandNotFoundException();

        if (getMiddlewares().stream()
                .map(m -> {
                    try {
                        return m.execute(i);
                    } catch (CommandException e) {
                        return false;
                    }
                })
                .filter(b -> !b)
                .count() > 0)
            throw new CommandMiddlewareException();

        return execute(i);
    }

}
