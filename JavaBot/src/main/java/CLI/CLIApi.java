package CLI;

import java.util.Scanner;

public class CLIApi {
    private final Scanner scanner;

    public CLIApi() {
        scanner = new Scanner(System.in);
    }

    public void registerBot(CLIProvider cli) {
        while (true) {
            Message message = getMessage();
            cli.onUpdateReceived(message);
        }
    }

    private Message getMessage() {
        String message_text = scanner.nextLine();
        return new Message(message_text);
    }
}
