package co.kaioru.retort;

import co.kaioru.retort.builder.ICommandBuilder;
import co.kaioru.retort.builder.IMiddlewareBuilder;
import co.kaioru.retort.exception.CommandException;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.function.Function;

public interface ICommand<I extends ICommandContext, O> extends ICommandExecutable<I, O> {

    String getName();

    String getDescription();

    void setDescription(String description);

    Set<String> getAliases();

    Set<ICommandMiddleware<I>> getMiddlewares();

    Set<ICommand<I, O>> getCommands();

    default void registerAlias(String alias) {
        getAliases().add(alias);
    }

    default void registerAliases(Collection<? extends String> aliases) {
        getAliases().addAll(aliases);
    }

    default void deregisterAlias(String alias) {
        getAliases().remove(alias);
    }

    default void registerMiddleware(ICommandMiddleware<I> middleware) {
        getMiddlewares().add(middleware);
    }

    default void registerMiddlewares(Collection<? extends ICommandMiddleware<I>> middlewares) {
        getMiddlewares().addAll(middlewares);
    }

    default void deregisterMiddleware(ICommandMiddleware<I> middleware) {
        getMiddlewares().remove(middleware);
    }

    default void registerCommand(ICommand<I, O> command) {
        getCommands().add(command);
    }

    default void registerCommands(Collection<? extends ICommand<I, O>> commands) {
        getCommands().addAll(commands);
    }

    default void deregisterCommand(ICommand<I, O> command) {
        getCommands().remove(command);
    }

    List<ICommand<I, O>> getCommand(String name);

    O process(I i) throws CommandException;

}
