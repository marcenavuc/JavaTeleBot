package CLI;

import org.telegram.telegrambots.api.objects.Location;
import org.telegram.telegrambots.api.objects.Update;

import java.util.Date;

public class Message {
    public int userId;
    public long chatId;
    public String text;
    public String user;
    public Location location;

    public Message(String text) {
        this.text = text;
        this.user = System.getProperty("user.name");
        this.userId = user.hashCode();
    }

    public Message(Update update) {
        text = update.getMessage().getText();
        user = update.getMessage().getFrom().getUserName();
        userId = update.getMessage().getFrom().getId();
        chatId = update.getMessage().getChatId();
        location = update.getMessage().getLocation();
    }
}