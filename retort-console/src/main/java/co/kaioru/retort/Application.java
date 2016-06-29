package co.kaioru.retort;

import co.kaioru.retort.command.Command;

import java.util.LinkedList;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Application {

	private static CommandRegistry registry = new CommandRegistry();
	private static ExecutorService executor = Executors.newSingleThreadExecutor();

	public static void main(String[] args) {
		registry.registerCommand(new Command() {
			@Override
			public String getName() {
				return "ping";
			}

			@Override
			public String getDesc() {
				return "Pong!";
			}

			@Override
			public Optional<Object> execute(LinkedList<String> args) throws Exception {
				System.out.println("pong!");
				return Optional.empty();
			}
		});

		executor.submit(new ConsoleListener(registry));
	}

}
