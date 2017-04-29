package co.kaioru.retort.annotation;

import co.kaioru.retort.*;
import co.kaioru.retort.annotation.adapter.AliasAnnotationProcessor;
import co.kaioru.retort.annotation.adapter.CommandAnnotationGenerator;
import co.kaioru.retort.annotation.adapter.DescriptionAnnotationProcessor;
import co.kaioru.retort.annotation.adapter.ReferenceAnnotationGenerator;
import co.kaioru.retort.annotation.builder.AnnotationFactoryBuilder;
import co.kaioru.retort.annotation.type.Alias;
import co.kaioru.retort.annotation.type.Command;
import co.kaioru.retort.annotation.type.Description;
import co.kaioru.retort.annotation.type.Reference;
import co.kaioru.retort.builder.CommandBuilder;
import co.kaioru.retort.exception.CommandException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class AnnotationTest {

    @Rule
    public ExpectedException exceptions = ExpectedException.none();

    private ICommandRegistry<ICommandContext, Boolean> commandRegistry;
    private AnnotatedCommands annotatedCommands;

    @Before
    public void setup() {
        this.commandRegistry = new CommandRegistry<>("registry");
        this.annotatedCommands = new AnnotatedCommands();
    }

    @Test
    public void annotationFactory() throws CommandException {
        commandRegistry.registerCommands(new AnnotationFactoryBuilder<ICommandContext, Boolean>()
                .withAdapter(new CommandAnnotationGenerator<>(ICommandContext.class, Boolean.class))
                .withAdapter(new DescriptionAnnotationProcessor<>())
                .withAdapter(new AliasAnnotationProcessor<>())
                .withObject(annotatedCommands)
                .build());

        assertTrue(commandRegistry.execute(new CommandContext(), "true"));
        assertTrue(commandRegistry.execute(new CommandContext(), "annotated"));
        assertTrue(commandRegistry.execute(new CommandContext(), "alias"));
        assertEquals("description", commandRegistry.getCommand("annotated").get(0).getDescription());
    }

    @Test
    public void annotationReference() throws CommandException {
        commandRegistry.registerCommands(new AnnotationFactoryBuilder<ICommandContext, Boolean>()
                .withAdapter(new ReferenceAnnotationGenerator<>())
                .withObject(annotatedCommands)
                .build());

        assertFalse(commandRegistry.execute(new CommandContext(), "false"));
    }

    private class AnnotatedCommands {

        @Command("true")
        public Boolean trueCommand(ICommandContext context) {
            return true;
        }

        @Command("annotated")
        @Alias("alias")
        @Description("description")
        public Boolean annotatedCommand(ICommandContext context) {
            return true;
        }

        @Reference
        public ICommand<ICommandContext, Boolean> getFalseCommand() {
            return new CommandBuilder<ICommandContext, Boolean>("false")
                    .build(context -> false);
        }

    }

}
