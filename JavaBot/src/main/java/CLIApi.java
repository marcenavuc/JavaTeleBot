import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.exceptions.TelegramApiRequestException;

import java.util.Scanner;

public class CLIApi {
    private final Scanner scanner;

    public CLIApi() {
        scanner = new Scanner(System.in);
    }

    public void registerBot(TelegramProvider provider) throws TelegramApiRequestException {
//        bot.clearWebhook();
//        BotSession session = (BotSession)ApiContext.getInstance(BotSession.class);
//        session.setToken(bot.getBotToken());
//        session.setOptions(bot.getOptions());
//        session.setCallback(bot);
//        session.start();
//        return session;
        while (true) {
            String message_text = scanner.nextLine();
//            String answer = getAnswer(message_text);
            provider.onUpdateReceived(getUpdateFromMessage(message_text));
//            System.out.println(answer);
        }
    }

    public Update getUpdateFromMessage(String message_text) {
        Update update = new Update();
        return update;
    }
}
