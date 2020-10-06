package Bot.Commands;

import Bot.Models.User;
import Bot.Repository;
import CLI.Message;

import java.io.IOException;

public class Subscribe extends Command {

    @Override
    public String execute(Message message, Repository manager) throws IOException {
        User user = manager.getUser(message.userId);
        if (user != null && user.isSubscribed) {
            return user.name + ", Вы уже подписаны!";
        } else {
            if (user == null) {
                user = new User(message.userId, message.user, null, false);
                manager.addUser(user);
            }

            user.isSubscribed = true;
            manager.updateUser(user);
            return user.name + ", Спасибо! за подписку";
        }
    }
}
