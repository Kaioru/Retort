package co.kaioru.retort;

import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

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

}
