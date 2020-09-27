package CLI;

import CLI.CLIUpdate;

import java.util.Scanner;

public class CLIApi {
    private final Scanner scanner;

    public CLIApi() {
        scanner = new Scanner(System.in);
    }

    public void registerBot(CLIProvider cli) {
        while (true) {
            CLIUpdate update = getMessage();
            cli.onUpdateReceived(update);
        }
    }

    private CLIUpdate getMessage() {
        String message_text = scanner.nextLine();
        return new CLIUpdate(message_text);
    }
}
