package co.kaioru.retort.annotation;

import co.kaioru.retort.TestCommandRegistry;
import co.kaioru.retort.annotatation.TestAnnotationFactoryBuilder;
import co.kaioru.retort.annotation.impl.adapter.AliasAnnotationProcessor;
import co.kaioru.retort.annotation.impl.adapter.CommandAnnotationGenerator;
import co.kaioru.retort.annotation.impl.adapter.DescriptionAnnotationProcessor;
import co.kaioru.retort.impl.CommandContext;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

public class JavaAnnotationTest {

    @Rule
    public ExpectedException exceptions = ExpectedException.none();

    private TestCommandRegistry registry;

    @Before
    public void setup() {
        this.registry = new TestCommandRegistry();
    }

    @Test
    public void annotationGenerating() {
        registry.registerCommands(new TestAnnotationFactoryBuilder()
                .withAdapter(new CommandAnnotationGenerator<>(CommandContext.class, Boolean.class))
                .withObject(new AnnotationCommands())
                .build());

        assertEquals(2, registry.getCommands().size());
        assertTrue(registry.process("true"));
        assertFalse(registry.process("false"));
    }

    @Test
    public void annotationProcessing() {
        registry.registerCommands(new TestAnnotationFactoryBuilder()
                .withAdapter(new CommandAnnotationGenerator<>(CommandContext.class, Boolean.class))
                .withAdapter(new AliasAnnotationProcessor<>())
                .withAdapter(new DescriptionAnnotationProcessor<>())
                .withObject(new AnnotationCommands())
                .build());

        assertEquals("false", registry.getCommand("false").stream().findFirst().get().getDescription());
        assertFalse(registry.process("nottrue"));
    }

    class AnnotationCommands {

        @Command("true")
        public Boolean trueCommand(CommandContext context) {
            return true;
        }

        @Command("false")
        @Alias("nottrue")
        @Description("false")
        public Boolean falseCommand(CommandContext context) {
            return false;
        }

    }

}
