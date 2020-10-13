package Bot.Commands;

import Bot.Models.User;
import Bot.UserRepository;
import CLI.Message;

import java.io.IOException;

public class Subscribe extends Command {

    @Override
    public String execute(Message message, UserRepository manager) throws IOException {
        User user = manager.getUser(message.userId);
        if (user != null && user.isSubscribed) {
            return user.name + ", Вы уже подписаны! \uD83D\uDE0F";
        } else {
            user.isSubscribed = true;
            manager.updateUser(user);
            return user.name + ", Спасибо за подписку! \uD83D\uDE0C";
        }
    }
}
