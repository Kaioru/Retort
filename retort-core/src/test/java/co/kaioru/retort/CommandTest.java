package co.kaioru.retort;

import co.kaioru.retort.command.Command;
import co.kaioru.retort.util.annotation.AnnotatedCommand;
import co.kaioru.retort.util.annotation.DefaultCommandAnnotator;
import co.kaioru.retort.util.annotation.ReferencedCommand;
import co.kaioru.retort.util.builder.DefaultCommandBuilder;
import org.junit.Test;

import java.time.Instant;
import java.util.LinkedList;

import static co.kaioru.retort.util.CommandUtil.executeCommand;
import static co.kaioru.retort.util.CommandUtil.getArgsFromText;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class CommandTest {

    private static final CommandRegistry registry = new CommandRegistry();

    @Test
    public void annotation() throws Exception {
        Commands commands = new Commands();
        new DefaultCommandAnnotator()
                .registerAnnotations(registry, commands)
                .registerReferences(registry, commands);

        executeCommand(registry, "dependent first second");
        executeCommand(registry, "dependent independent first second");
        executeCommand(registry, "referenced first second");
    }

    @Test
    public void builder() throws Exception {
        registry
                .registerCommand(new DefaultCommandBuilder("built")
                        .command(new DefaultCommandBuilder("inner")
                                .build(args -> {
                                    assertEquals(args.removeFirst(), "first");
                                    assertEquals(args.removeFirst(), "second");
                                }))
                        .build(args -> {
                            assertEquals(args.removeFirst(), "first");
                            assertEquals(args.removeFirst(), "second");
                        }));

        executeCommand(registry, "built first second");
        executeCommand(registry, "built inner first second");
    }

    @Test
    public void classes() throws Exception {
        Command built = new Command() {
            @Override
            public String getName() {
                return "class";
            }

            @Override
            public String getDesc() {
                return "desc";
            }

            @Override
            public void execute(LinkedList<String> args) throws Exception {
                assertEquals(args.removeFirst(), "first");
                assertEquals(args.removeFirst(), "second");
            }
        };

        built.registerCommand(new Command() {
            @Override
            public String getName() {
                return "inner";
            }

            @Override
            public String getDesc() {
                return "No description";
            }

            @Override
            public void execute(LinkedList<String> args) throws Exception {
                assertEquals(args.removeFirst(), "first");
                assertEquals(args.removeFirst(), "second");
            }
        });
        registry.registerCommand(built);

        executeCommand(registry, "class first second");
        executeCommand(registry, "class inner first second");
    }

    @Test
    public void argsWithSpace() throws Exception {
        registry.registerCommand(new DefaultCommandBuilder("long")
                .build(args -> {
                    assertEquals(args.removeFirst(), "argument in quotes");
                    assertEquals(args.removeFirst(), "argument in double quotes");
                }));

        executeCommand(registry, "long 'argument in quotes' \"argument in double quotes\"");
    }

    @Test
    public void multiArgsCommand() throws Exception {
        registry.registerCommand(new MultiArgCommand() {

            @Override
            public String getName() {
                return "multi";
            }

            @Override
            public String getDesc() {
                return "No description";
            }

            @Override
            public void execute(LinkedList<String> args, Instant instant) {
                assertNotNull(instant);
            }

        });

        executeCommand(registry, "multi", Instant.now());
    }

    class Commands {

        @AnnotatedCommand(
                name = "independent"
        )
        public void independentCommand(LinkedList<String> args) {
            assertEquals(args.removeFirst(), "first");
            assertEquals(args.removeFirst(), "second");
        }

        @AnnotatedCommand(
                name = "dependent",
                commands = {"independent"}
        )
        public void dependentCommand(LinkedList<String> args) {
            assertEquals(args.removeFirst(), "first");
            assertEquals(args.removeFirst(), "second");
        }

        @ReferencedCommand
        public Command getReferencedCommand() {
            return new Command() {

                @Override
                public String getName() {
                    return "referenced";
                }

                @Override
                public String getDesc() {
                    return "No description";
                }

                @Override
                public void execute(LinkedList<String> args) throws Exception {
                    assertEquals(args.removeFirst(), "first");
                    assertEquals(args.removeFirst(), "second");
                }

            };
        }

    }

    abstract class MultiArgCommand extends Command {

        @Override
        public void execute(LinkedList<String> args) throws Exception {
            return;
        }

        public abstract void execute(LinkedList<String> args, Instant instant);

    }

}
