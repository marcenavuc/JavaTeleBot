import java.util.Scanner;

public class CLIProvider {
    private final Scanner scanner;
    private final Bot bot;

    public CLIProvider(Bot newBot) {
        scanner = new Scanner(System.in);
        bot = newBot;

        // Start Messaging
        System.out.println(getAnswer("/start"));
    }

    public void onUpdateReceived() {
        while (true) {
            String message_text = scanner.nextLine();
            String answer = getAnswer(message_text);
            System.out.println(answer);
        }
    }

    public String getAnswer(String question) {
        return bot.takeAnswer(question);
    }
}
