package co.kaioru.retort.reflection;

import co.kaioru.retort.BaseCommandContext;
import co.kaioru.retort.BaseCommandRegistry;
import co.kaioru.retort.ICommandContext;
import co.kaioru.retort.ICommandRegistry;
import co.kaioru.retort.annotation.builder.BaseAnnotationFactoryBuilder;
import co.kaioru.retort.annotation.type.Command;
import co.kaioru.retort.exception.CommandException;
import co.kaioru.retort.exception.CommandInvalidSyntaxException;
import co.kaioru.retort.reflection.builder.BaseReflectionGeneratorBuilder;
import co.kaioru.retort.reflection.exception.CommandProviderException;
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
        this.commandRegistry = new BaseCommandRegistry<>("registry");
        this.reflectedCommands = new ReflectedCommands();
    }

    @Test
    public void reflectionProvider() throws CommandException {
        commandRegistry.registerCommands(new BaseAnnotationFactoryBuilder<ICommandContext, Boolean>()
                .withAdapter(new BaseReflectionGeneratorBuilder<>(Boolean.class)
                        .withProvider(new StringReflectionProvider())
                        .withProvider(new BooleanReflectionProvider())
                        .withProvider(new IntegerReflectionProvider())
                        .build())
                .withObject(reflectedCommands)
                .build());

        assertTrue(commandRegistry.execute(new BaseCommandContext(), "invert false"));
        assertTrue(commandRegistry.execute(new BaseCommandContext(), "equal 1 1"));
        assertFalse(commandRegistry.execute(new BaseCommandContext(), "equal 1 2"));

        assertTrue(commandRegistry.execute(new BaseCommandContext(), "has hello"));
        assertFalse(commandRegistry.execute(new BaseCommandContext(), "has"));
    }

    @Test
    public void reflectionInvalidSyntaxException() throws CommandException {
        commandRegistry.registerCommands(new BaseAnnotationFactoryBuilder<ICommandContext, Boolean>()
                .withAdapter(new BaseReflectionGeneratorBuilder<>(Boolean.class)
                        .withProvider(new BooleanReflectionProvider())
                        .build())
                .withObject(reflectedCommands)
                .build());

        exceptions.expect(CommandInvalidSyntaxException.class);
        System.out.println(commandRegistry.execute(new BaseCommandContext(), "invert"));
    }

    @Test
    public void reflectionProviderException() throws CommandException {
        commandRegistry.registerCommands(new BaseAnnotationFactoryBuilder<ICommandContext, Boolean>()
                .withAdapter(new BaseReflectionGeneratorBuilder<>(Boolean.class)
                        .build())
                .withObject(reflectedCommands)
                .build());

        exceptions.expect(CommandProviderException.class);
        commandRegistry.execute(new BaseCommandContext(), "has");
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
