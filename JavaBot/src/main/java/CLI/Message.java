package CLI;

import org.telegram.telegrambots.api.objects.Update;

public class Message {
    public int userId;
    public String text;
    public String user;

    public Message(String text) {
        this.text = text;
        this.user = System.getProperty("user.name");
        this.userId = user.hashCode();
    }

    public Message(Update update) {
        text = update.getMessage().getText();
        user = update.getMessage().getFrom().getUserName();
        userId = update.getMessage().getFrom().getId();
    }
}
