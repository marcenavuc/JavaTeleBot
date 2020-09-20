import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import java.lang.reflect.Method;
import java.util.HashMap;

// Бот должен хранить только методы и классы
public class Bot extends TelegramLongPollingBot {
    private static final String BotName = "Glinomes";
    private static final String Token = "1179069441:AAGjBmqZvasfIAKWrTcPeq4Igw1Kq41_qEQ";

    private static HashMap<String, String> miniDb = new HashMap<String, String>();

    public Bot(){
        miniDb.put("/start", "Привет, я глиномес");
        miniDb.put("/help", "Я ничего не умею(((");
        miniDb.put("хз", "Я ничего не понял((");
    }

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
        return miniDb.get(key);
    }
}
