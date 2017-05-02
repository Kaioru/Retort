package co.kaioru.retort;

import co.kaioru.retort.exceptions.CommandNotBuiltException;
import co.kaioru.retort.exceptions.CommandRegistryException;
import co.kaioru.retort.impl.CommandContext;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Arrays;

import static org.junit.Assert.*;

public class JavaCommandTest {

    @Rule
    public ExpectedException exceptions = ExpectedException.none();

    private TestCommandRegistry registry;

    @Before
    public void setup() {
        this.registry = new TestCommandRegistry();
    }

    @Test
    public void commandBuilder() {
        ICommand<CommandContext, Boolean> command =
                new TestCommandBuilder("builder")
                        .withDescription("hello")
                        .withAlias("creator")
                        .withAliases(Arrays.asList("maker", "author"))
                        .withMiddleware(Arrays.asList(
                                new TestMiddlewareBuilder().build(context -> true),
                                new TestMiddlewareBuilder().build(context -> true)
                        ))
                        .withCommands(Arrays.asList(
                                new TestCommandBuilder("sub1").build(context -> true),
                                new TestCommandBuilder("sub2")
                        ))
                        .build(context -> true);
        registry.registerCommand(command);

        assertEquals("builder", command.getName());
        assertEquals("hello", command.getDescription());
        assertEquals(3, command.getAliases().size());
        assertEquals(2, command.getMiddleware().size());
        assertEquals(2, command.getCommands().size());
        assertTrue(registry.process("builder"));

        exceptions.expect(CommandNotBuiltException.class);
        registry.process("builder sub2");
    }

    @Test
    public void commandExecution() {
        registry.registerCommand(new TestCommandBuilder("true")
                .build(context -> true));

        assertTrue(registry.process("true"));
        assertTrue(registry.process("t"));

        exceptions.expect(CommandRegistryException.class);
        registry.process("false");
    }

    @Test
    public void commandRouting() {
        registry.registerCommand(new TestCommandBuilder("true")
                .build(context -> true));
        registry.registerCommand(new TestCommandBuilder("false")
                .build(context -> false));

        assertTrue(registry.process("true"));
        assertTrue(registry.process("t"));
        assertFalse(registry.process("false"));
        assertFalse(registry.process("f"));

        registry.registerCommand(new TestCommandBuilder("outer")
                .withCommand(new TestCommandBuilder("inner")
                        .build(context -> false))
                .build(context -> true));

        assertTrue(registry.process("outer"));
        assertTrue(registry.process("outer false"));
        assertFalse(registry.process("outer inner"));
        assertFalse(registry.process("outer inner true"));
    }

}
