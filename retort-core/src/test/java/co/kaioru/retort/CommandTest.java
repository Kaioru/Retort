package co.kaioru.retort;

import co.kaioru.retort.builder.CommandBuilder;
import co.kaioru.retort.exception.CommandException;
import co.kaioru.retort.exception.CommandNotBuiltException;
import co.kaioru.retort.exception.CommandNotFoundException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

public class CommandTest {

    @Rule
    public ExpectedException exceptions = ExpectedException.none();

    private ICommandRegistry<ICommandContext, Boolean> commandRegistry;
    private ICommand<ICommandContext, Boolean> command;

    @Before
    public void setup() {
        this.commandRegistry = new CommandRegistry<>("registry");
        this.command = new CommandBuilder<ICommandContext, Boolean>("hello")
                .build(ctx -> true);
    }

    @Test
    public void commandRetrieval() {
        commandRegistry.registerCommand(command);

        assertEquals(command, commandRegistry.getCommand("hello").get(0));
        assertEquals(command, commandRegistry.getCommand("h").get(0));
    }

    @Test
    public void commandExecution() throws CommandException {
        commandRegistry.registerCommand(command);

        assertTrue(commandRegistry.execute(new CommandContext(), "hello"));
        assertTrue(commandRegistry.execute(new CommandContext(), "h"));

        command.registerCommand(new CommandBuilder<ICommandContext, Boolean>("inside")
                .build(ctx -> false));

        assertFalse(commandRegistry.execute(new CommandContext(), "hello inside"));
        assertFalse(commandRegistry.execute(new CommandContext(), "h i"));
        assertTrue(commandRegistry.execute(new CommandContext(), "hello"));
    }

    @Test
    public void commandException() throws CommandException {
        exceptions.expect(CommandNotFoundException.class);
        commandRegistry.execute(new CommandContext(), "hello");

        exceptions.expect(CommandNotBuiltException.class);
        commandRegistry.registerCommand(new CommandBuilder<>("unbuilt"));
        commandRegistry.execute(new CommandContext(), "unbuilt");
    }

    @Test
    public void commandRegex() throws CommandException {
        commandRegistry.registerCommand(new CommandBuilder<ICommandContext, Boolean>("echo")
                .build(ctx -> ctx.getArgs().remove().equals("this text is echoed!")));

        assertFalse(commandRegistry.execute(new CommandContext(), "echo this text is echoed!"));
        assertTrue(commandRegistry.execute(new CommandContext(), "echo 'this text is echoed!'"));
        assertTrue(commandRegistry.execute(new CommandContext(), "echo \"this text is echoed!\""));
    }

}
