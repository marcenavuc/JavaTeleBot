import org.telegram.telegrambots.api.methods.BotApiMethod;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.AbsSender;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import java.util.HashMap;

public class TelegramProvider extends TelegramLongPollingBot {
    private static final String BotName = "Glinomes";
    private static final String Token = "1179069441:AAGjBmqZvasfIAKWrTcPeq4Igw1Kq41_qEQ";

    @Override
    public void onUpdateReceived(Update update) {

        if (update.hasMessage() && update.getMessage().hasText()) {
            String message_text = update.getMessage().getText();
            long chat_id = update.getMessage().getChatId();

            String answer = getAnswer(message_text);

            SendMessage message = new SendMessage().setChatId(chat_id).setText(answer);
            try {
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

    public String getAnswer(String key) {
        return null;
    }
}
