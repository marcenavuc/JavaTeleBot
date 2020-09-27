import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.exceptions.TelegramApiException;

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

    public void newOnUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String message_text = update.getMessage().getText();
            long chat_id = update.getMessage().getChatId();

            String answer = getAnswer(message_text);

            SendMessage message = new SendMessage().setChatId(chat_id).setText(answer);
//            try {
//                System.out.println(message); // Заменить на логирование
//                execute(message); // Sending our message object to user
//            } catch (TelegramApiException e) {
//                e.printStackTrace();
//            }
        }
    }

    public String getAnswer(String question) {
        return bot.takeAnswer(question);
    }
}
