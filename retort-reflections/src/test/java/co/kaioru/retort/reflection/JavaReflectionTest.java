package co.kaioru.retort.reflection;

import co.kaioru.retort.TestCommandRegistry;
import co.kaioru.retort.annotatation.TestAnnotationFactoryBuilder;
import co.kaioru.retort.annotatation.TestReflectionGeneratorBuilder;
import co.kaioru.retort.annotation.Command;
import co.kaioru.retort.impl.CommandContext;
import co.kaioru.retort.reflection.exceptions.ReflectionNotOptionalException;
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

        assertEquals(5, registry.getCommands().size());
        assertTrue(registry.process("true"));
        assertFalse(registry.process("invert true"));
        assertTrue(registry.process("provider hello 1 1 1 1.0 1.0 true"));
        assertTrue(registry.process("exists hello"));
        assertFalse(registry.process("exists"));

        exceptions.expect(ReflectionProviderException.class);
        registry.process("error");
    }

    @Test
    public void reflectionOptional() {
        registry.registerCommands(new TestAnnotationFactoryBuilder()
                .withAdapter(new TestReflectionGeneratorBuilder()
                        .withProviders(Providers.primitiveProviders())
                        .build())
                .withObject(new ReflectionCommands())
                .build());

        exceptions.expect(ReflectionNotOptionalException.class);
        assertFalse(registry.process("invert"));
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

        @Reflect
        @Command("provider")
        public Boolean getProvider(
                String s,
                byte b,
                int i,
                long l,
                double d,
                float f,
                boolean bool
        ) {
            return bool;
        }

        @Reflect
        @Command("exists")
        public Boolean getExists(@Optional String string) {
            return string != null;
        }

    }

}
