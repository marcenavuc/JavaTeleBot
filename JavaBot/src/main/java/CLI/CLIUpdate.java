package CLI;

import org.telegram.telegrambots.api.objects.Update;

public class CLIUpdate {
    public String text;
    public String user;

    public CLIUpdate(String newText) {
        text = newText;
        user = System.getProperty("user.name");
    }

    public CLIUpdate(Update update) {
        text = update.getMessage().getText();
        user = update.getMessage().getAuthorSignature();
    }
}
