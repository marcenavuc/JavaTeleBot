package Bot;

import Bot.Commands.*;
import CLI.Message;

import java.io.IOException;
import java.util.HashMap;

public class Bot {
    private final HashMap<String, Command> commands = new HashMap<String, Command>();
    private final String ifNotFound = "Я ничего не понял(((";
    private final String ErrorMessage = "AAAAA, я сломался!!!! ЧТо ты наделал?";
    public Repository userManager = new Repository();


    public Bot() {
        commands.put("/start", new Start());
        commands.put("/help", new Help());
        commands.put("/weather", new Weather());
        commands.put("/subscribe", new Subscribe());
        commands.put("/unsubscribe", new Unsubscribe());
    }

    public String takeAnswer(Message message) {
        Command handler = commands.get(message.text);
        try {
            return handler != null ? handler.execute(message, userManager) : ifNotFound;
        } catch (IOException e) {
            e.printStackTrace();
            return ErrorMessage;
        }
    }
}
