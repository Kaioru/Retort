package co.kaioru.retort;

import co.kaioru.retort.exceptions.CommandRegistryException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class JavaCommandTest {

    @Rule
    public ExpectedException exceptions = ExpectedException.none();

    private TestCommandRegistry registry;

    @Before
    public void setup() {
        this.registry = new TestCommandRegistry();
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
