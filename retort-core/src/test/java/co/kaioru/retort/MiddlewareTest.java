package co.kaioru.retort;

import co.kaioru.retort.builder.CommandBuilder;
import co.kaioru.retort.builder.MiddlewareBuilder;
import co.kaioru.retort.exception.CommandException;
import co.kaioru.retort.exception.CommandMiddlewareException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertTrue;

public class MiddlewareTest {

    @Rule
    public ExpectedException exceptions = ExpectedException.none();

    private ICommandRegistry<ICommandContext, Boolean> commandRegistry;
    private ICommand<ICommandContext, Boolean> command;

    @Before
    public void setup() {
        this.commandRegistry = new BaseCommandRegistry<>("registry");
        this.command = new CommandBuilder<ICommandContext, Boolean>("true")
                .build(ctx -> true);
    }

    @Test
    public void middlewarePass() throws CommandException {
        command.registerMiddleware(new MiddlewareBuilder<>()
                .build(context -> true));
        commandRegistry.registerCommand(command);

        assertTrue(commandRegistry.execute(new BaseCommandContext(), "true"));
    }

    @Test
    public void middlewareFail() throws CommandException {
        command.registerMiddleware(new MiddlewareBuilder<>()
                .build(context -> false));
        commandRegistry.registerCommand(command);

        exceptions.expect(CommandMiddlewareException.class);
        commandRegistry.execute(new BaseCommandContext(), "true");
    }

    @Test
    public void middlewareNotBuiltException() throws CommandException {
        command.registerMiddleware(new MiddlewareBuilder<>());
        commandRegistry.registerCommand(command);

        exceptions.expect(CommandMiddlewareException.class);
        commandRegistry.execute(new BaseCommandContext(), "true");
    }

}
