package co.kaioru.retort;

import co.kaioru.retort.builder.BaseCommandBuilder;
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
        this.commandRegistry = new BaseCommandRegistry<>("registry");
        this.command = new BaseCommandBuilder<ICommandContext, Boolean>("hello")
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

        assertTrue(commandRegistry.execute(new BaseCommandContext(), "hello"));
        assertTrue(commandRegistry.execute(new BaseCommandContext(), "h"));

        command.registerCommand(new BaseCommandBuilder<ICommandContext, Boolean>("inside")
                .build(ctx -> false));

        assertFalse(commandRegistry.execute(new BaseCommandContext(), "hello inside"));
        assertFalse(commandRegistry.execute(new BaseCommandContext(), "h i"));
        assertTrue(commandRegistry.execute(new BaseCommandContext(), "hello"));
    }

    @Test
    public void commandNotFoundException() throws CommandException {
        exceptions.expect(CommandNotFoundException.class);
        commandRegistry.execute(new BaseCommandContext(), "hello");
    }

    @Test
    public void commandNotBuiltException() throws CommandException {
        exceptions.expect(CommandNotBuiltException.class);
        commandRegistry.registerCommand(new BaseCommandBuilder<>("unbuilt"));
        commandRegistry.execute(new BaseCommandContext(), "unbuilt");
    }

    @Test
    public void commandRegex() throws CommandException {
        commandRegistry.registerCommand(new BaseCommandBuilder<ICommandContext, Boolean>("echo")
                .build(ctx -> ctx.getArgs().remove().equals("this text is echoed!")));

        assertFalse(commandRegistry.execute(new BaseCommandContext(), "echo this text is echoed!"));
        assertTrue(commandRegistry.execute(new BaseCommandContext(), "echo 'this text is echoed!'"));
        assertTrue(commandRegistry.execute(new BaseCommandContext(), "echo \"this text is echoed!\""));
    }

}
