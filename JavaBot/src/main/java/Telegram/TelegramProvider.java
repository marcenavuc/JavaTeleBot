package Telegram;
import Bot.Bot;
import CLI.Message;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

public class TelegramProvider extends TelegramLongPollingBot {
    private static final String BotName = "WeatherBot";
    private static String Token;
    private final Bot bot;

    public TelegramProvider(Bot newBot, String telegramToken) {
        super();
        bot = newBot;
        Token = telegramToken;
    }

    @Override
    public void onUpdateReceived(Update update) {

        if (update.hasMessage()) {
            long chatId = update.getMessage().getChatId();
            String answer = getAnswer(update);
            SendMessageToTelegram(chatId, answer);
        }
    }

    public void SendMessageToTelegram(long chatId, String text) {
        SendMessage message = new SendMessage().setChatId(chatId).setText(text);
        try {
            System.out.println(message); // Заменить на логирование
            execute(message); // Sending our message object to user
        } catch (TelegramApiException e) {
            e.printStackTrace();
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
