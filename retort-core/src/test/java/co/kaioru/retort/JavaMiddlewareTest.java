package co.kaioru.retort;

import co.kaioru.retort.exceptions.CommandMiddlewareException;
import co.kaioru.retort.exceptions.CommandNotBuiltException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class JavaMiddlewareTest {

    @Rule
    public ExpectedException exceptions = ExpectedException.none();

    private TestCommandRegistry registry;

    @Before
    public void setup() {
        this.registry = new TestCommandRegistry();
    }

    @Test
    public void middlewareExecution() {
        registry.registerCommand(new TestCommandBuilder("true")
                .withMiddleware(new TestMiddlewareBuilder()
                        .build(context -> true))
                .build(context -> true));

        assertTrue(registry.process("true"));

        registry.registerCommand(new TestCommandBuilder("false")
                .withMiddleware(new TestMiddlewareBuilder()
                        .build(context -> false))
                .build(context -> false));
        exceptions.expect(CommandMiddlewareException.class);
        registry.process("false");
    }

    @Test
    public void middlewareNotBuilt() {
        registry.registerCommand(new TestCommandBuilder("true")
                .withMiddleware(new TestMiddlewareBuilder())
                .build(context -> true));

        assertNull(new TestMiddlewareBuilder().getExecutable());
        
        exceptions.expect(CommandNotBuiltException.class);
        registry.process("true");
    }

}
