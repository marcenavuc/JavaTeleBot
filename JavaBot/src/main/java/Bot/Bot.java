package Bot;

import Bot.Commands.*;
import Bot.Models.User;
import CLI.Message;

import java.util.ArrayList;
import java.util.HashMap;

public class Bot {
    private final HashMap<String, Command> commands = new HashMap<String, Command>();
    private final ArrayList<Command> stateCommands = new ArrayList<Command>();
    private final String ifNotFound = "Я ничего не понял(((";
    private final String ErrorMessage = "AAAAA, я сломался!!!! ЧТо ты наделал?";
    public UserRepository userRepository = new UserRepository();


    public Bot() {
        commands.put("/start", new Start());
        commands.put("/help", new Help());
        commands.put("/weather", new Weather());
        commands.put("/subscribe", new Subscribe());
        commands.put("/unsubscribe", new Unsubscribe());
        commands.put("/change", new SetLocation());

        stateCommands.add(0, new Help());
        stateCommands.add(1, new SetLocation());
    }

    public String takeAnswer(Message message) {
        User user = userRepository.getUser(message.userId);

        // Если к нам поступило сообщение от нового опльзователя
        if (user == null) {
            user = new User(message.userId, message.user, null, false);
            try {
                userRepository.addUser(user);
            } catch (Exception e) {
                e.printStackTrace();
                return ErrorMessage;
            }
        }

        Command command = user.state == 0
                ? commands.get(message.text)
                : stateCommands.get(user.state);

        try {
            return command != null ? command.execute(message, userRepository) : ifNotFound;
        } catch (Exception e) {
            e.printStackTrace();
            return ErrorMessage;
        }
    }
}
