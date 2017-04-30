package co.kaioru.retort.annotation;

import co.kaioru.retort.annotation.adapter.AliasAnnotationProcessor;
import co.kaioru.retort.annotation.adapter.CommandAnnotationGenerator;
import co.kaioru.retort.annotation.adapter.DescriptionAnnotationProcessor;
import co.kaioru.retort.annotation.adapter.ReferenceAnnotationGenerator;
import co.kaioru.retort.annotation.builder.BaseAnnotationFactoryBuilder;
import co.kaioru.retort.annotation.type.Alias;
import co.kaioru.retort.annotation.type.Command;
import co.kaioru.retort.annotation.type.Description;
import co.kaioru.retort.annotation.type.Reference;
import co.kaioru.retort.builder.BaseCommandBuilder;
import co.kaioru.retort.exception.CommandException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

public class AnnotationTest {

    @Rule
    public ExpectedException exceptions = ExpectedException.none();

    private ICommandRegistry<ICommandContext, Boolean> commandRegistry;
    private AnnotatedCommands annotatedCommands;

    @Before
    public void setup() {
        this.commandRegistry = new BaseCommandRegistry<>("registry");
        this.annotatedCommands = new AnnotatedCommands();
    }

    @Test
    public void annotationFactory() throws CommandException {
        commandRegistry.registerCommands(new BaseAnnotationFactoryBuilder<ICommandContext, Boolean>()
                .withAdapter(new CommandAnnotationGenerator<>(ICommandContext.class, Boolean.class))
                .withAdapter(new DescriptionAnnotationProcessor<>())
                .withAdapter(new AliasAnnotationProcessor<>())
                .withObject(annotatedCommands)
                .build());

        assertTrue(commandRegistry.execute(new BaseCommandContext(), "true"));
        assertTrue(commandRegistry.execute(new BaseCommandContext(), "annotated"));
        assertTrue(commandRegistry.execute(new BaseCommandContext(), "alias"));
        assertEquals("description", commandRegistry.getCommand("annotated").get(0).getDescription());
    }

    @Test
    public void annotationReference() throws CommandException {
        commandRegistry.registerCommands(new BaseAnnotationFactoryBuilder<ICommandContext, Boolean>()
                .withAdapter(new ReferenceAnnotationGenerator<>())
                .withObject(annotatedCommands)
                .build());

        assertFalse(commandRegistry.execute(new BaseCommandContext(), "false"));
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
            return new BaseCommandBuilder<ICommandContext, Boolean>("false")
                    .build(context -> false);
        }

    }

}
