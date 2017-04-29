package co.kaioru.retort;

import co.kaioru.retort.builder.CommandBuilder;
import co.kaioru.retort.exception.CommandException;
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

}
