package co.kaioru.retort.reflection;

import co.kaioru.retort.CommandContext;
import co.kaioru.retort.CommandRegistry;
import co.kaioru.retort.ICommandContext;
import co.kaioru.retort.ICommandRegistry;
import co.kaioru.retort.annotation.builder.AnnotationFactoryBuilder;
import co.kaioru.retort.annotation.type.Command;
import co.kaioru.retort.exception.CommandException;
import co.kaioru.retort.reflection.builder.ReflectionGeneratorBuilder;
import co.kaioru.retort.reflection.provider.BooleanReflectionProvider;
import co.kaioru.retort.reflection.provider.IntegerReflectionProvider;
import co.kaioru.retort.reflection.provider.StringReflectionProvider;
import co.kaioru.retort.reflection.type.Optional;
import co.kaioru.retort.reflection.type.Reflect;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ReflectionTest {

    @Rule
    public ExpectedException exceptions = ExpectedException.none();

    private ICommandRegistry<ICommandContext, Boolean> commandRegistry;
    private ReflectedCommands reflectedCommands;

    @Before
    public void setup() {
        this.commandRegistry = new CommandRegistry<>("registry");
        this.reflectedCommands = new ReflectedCommands();
    }

    @Test
    public void reflectionProvider() throws CommandException {
        commandRegistry.registerCommands(new AnnotationFactoryBuilder<ICommandContext, Boolean>()
                .withAdapter(new ReflectionGeneratorBuilder<>(Boolean.class)
                        .withProvider(new StringReflectionProvider())
                        .withProvider(new BooleanReflectionProvider())
                        .withProvider(new IntegerReflectionProvider())
                        .build())
                .withObject(reflectedCommands)
                .build());

        assertTrue(commandRegistry.execute(new CommandContext(), "invert false"));
        assertTrue(commandRegistry.execute(new CommandContext(), "equal 1 1"));
        assertFalse(commandRegistry.execute(new CommandContext(), "equal 1 2"));

        assertTrue(commandRegistry.execute(new CommandContext(), "has hello"));
        assertFalse(commandRegistry.execute(new CommandContext(), "has"));
    }

    private class ReflectedCommands {

        @Reflect
        @Command("invert")
        public Boolean getInvert(boolean input) {
            return !input;
        }

        @Reflect
        @Command("equal")
        public Boolean isEqual(int a, int b) {
            return a == b;
        }

        @Reflect
        @Command("has")
        public Boolean hasText(@Optional String text) {
            return text != null;
        }

    }

}
