package CLI;

import org.telegram.telegrambots.api.objects.Update;

public class CLIUpdate {
    public int userId;
    public String text;
    public String user;

    public CLIUpdate(String text) {
        this.text = text;
        this.user = System.getProperty("user.name");
        this.userId = user.hashCode();
    }

    public CLIUpdate(Update update) {
        text = update.getMessage().getText();
        user = update.getMessage().getAuthorSignature();
        userId = user.hashCode();
    }
}
