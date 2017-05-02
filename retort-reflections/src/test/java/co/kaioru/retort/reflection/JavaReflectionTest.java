package co.kaioru.retort.reflection;

import co.kaioru.retort.TestCommandRegistry;
import co.kaioru.retort.annotatation.TestAnnotationFactoryBuilder;
import co.kaioru.retort.annotatation.TestReflectionGeneratorBuilder;
import co.kaioru.retort.annotation.Command;
import co.kaioru.retort.impl.CommandContext;
import co.kaioru.retort.reflection.exceptions.ReflectionProviderException;
import co.kaioru.retort.reflection.impl.provider.Providers;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

public class JavaReflectionTest {

    @Rule
    public ExpectedException exceptions = ExpectedException.none();

    private TestCommandRegistry registry;

    @Before
    public void setup() {
        this.registry = new TestCommandRegistry();
    }

    @Test
    public void reflectionGenerating() {
        registry.registerCommands(new TestAnnotationFactoryBuilder()
                .withAdapter(new TestReflectionGeneratorBuilder()
                        .withProviders(Providers.primitiveProviders())
                        .build())
                .withObject(new ReflectionCommands())
                .build());

        assertEquals(3, registry.getCommands().size());
        assertTrue(registry.process("true"));
        assertFalse(registry.process("invert true"));

        exceptions.expect(ReflectionProviderException.class);
        registry.process("error");
    }

    class ReflectionCommands {

        @Reflect
        @Command("true")
        public Boolean getTrue() {
            return true;
        }

        @Reflect
        @Command("invert")
        public Boolean getInvert(boolean bool) {
            return !bool;
        }

        @Reflect
        @Command("error")
        public Boolean getError(CommandContext context) {
            return true;
        }

    }

}
