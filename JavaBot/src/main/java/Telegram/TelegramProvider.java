package Telegram;
import Bot.Bot;
import CLI.Message;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

public class TelegramProvider extends TelegramLongPollingBot {
    private static final String BotName = "QQuestionBot";
    private static final String Token = "1292220516:AAEwoFfrkYfm9sI2TY0HeTYCfV40NHwSALg";
    private final Bot bot;

    public TelegramProvider(Bot newBot) {
        super();
        bot = newBot;
    }

    @Override
    public void onUpdateReceived(Update update) {

        if (update.hasMessage() && update.getMessage().hasText()) {
            long chat_id = update.getMessage().getChatId();

            String answer = getAnswer(update);

            SendMessage message = new SendMessage().setChatId(chat_id).setText(answer);
            try {
                System.out.println(message); // Заменить на логирование
                execute(message); // Sending our message object to user
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public String getBotUsername() {
        return BotName;
    }

    @Override
    public String getBotToken() {
        return Token;
    }

    public String getAnswer(Update update) {
        return bot.takeAnswer(new Message(update));
    }
}
