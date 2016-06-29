package co.kaioru.retort;

import co.kaioru.retort.util.CommandUtil;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ConsoleListener implements Runnable {

	private final CommandRegistry registry;

	public ConsoleListener(CommandRegistry registry) {
		this.registry = registry;
	}

	@Override
	public void run() {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Retort is now running.");
		System.out.println("Use 'help' to print a list of commands");
		System.out.println();

		while (!Thread.interrupted()) {
			try {
				String input = br.readLine();

				CommandUtil.executeCommand(registry, CommandUtil.getArgsFromText(input));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
