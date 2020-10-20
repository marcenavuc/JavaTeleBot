package Bot.Commands;

import Bot.Models.User;
import Bot.UserRepository;
import CLI.Message;

import java.io.IOException;

public class Unsubscribe extends Command {

    @Override
    public String execute(Message message, UserRepository userRepository) throws IOException {
        User user = userRepository.getUser(message.userId);
        if (user != null && !user.isSubscribed) {
            return user.name + ", Вы eщё не подписаны!";
        } else {
            user.isSubscribed = false;
            userRepository.updateUser(user);
            return user.name + ", Вы отписаны";
        }
    }
}