package Bot;

import Bot.Commands.*;
import CLI.CLIUpdate;
import java.util.HashMap;

public class Bot {
    private final HashMap<String, Command> commands = new HashMap<String, Command>();
    private final String ifNotFound = "Я ничего не понял(((";
    public UserManager userManager = new UserManager();


    public Bot() {
        commands.put("/start", new Start());
        commands.put("/help", new Help());
        commands.put("/weather", new Weather());
        commands.put("/subscribe", new Subscribe());
        commands.put("/unsubscribe", new Unsubscribe());
    }

    public String takeAnswer(CLIUpdate update) {
        Command handler = commands.get(update.text);
        return handler != null ? handler.execute(update, userManager) : ifNotFound;
    }
}
