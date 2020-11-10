package Bot;

import Bot.Commands.*;
import Bot.Models.States;
import Bot.Models.User;
import CLI.Message;

import java.util.HashMap;

public class Bot {
    private final HashMap<String, Command> commands = new HashMap<String, Command>();
    private final HashMap<States, Command> stateCommands = new HashMap<States, Command>();
    private final String ifNotFound = "Я ничего не понял(((";
    private final String ErrorMessage = "AAAAA, я сломался!!!! ЧТо ты наделал?";
    private String weatherKey;
    public UserRepository userRepository = new UserRepository();


    public Bot(String weatherKey) {
        this.weatherKey = weatherKey;

        commands.put("/start", new Start());
        commands.put("/help", new Help());
        commands.put("/weather", new Weather(this.weatherKey));
        commands.put("/subscribe", new Subscribe());
        commands.put("/unsubscribe", new Unsubscribe());
        commands.put("/change", new SetLocation());

        stateCommands.put(States.DEFAULT, new Help());
        stateCommands.put(States.CHANGELOCATION, new SetLocation());
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

        Command command = user.state == States.DEFAULT
                ? commands.get(message.text)
                : stateCommands.get(user.state);

        try {
            // Если ввели комманду
            if (command != null)
                return command.execute(message, userRepository);

            user.state = States.FASTFORECAST;
            return commands.get("/change").execute(message, userRepository);
        } catch (Exception e) {
            e.printStackTrace();
            return ErrorMessage;
        }
    }
}
