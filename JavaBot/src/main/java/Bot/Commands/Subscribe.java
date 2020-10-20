package Bot.Commands;

import Bot.Models.User;
import Bot.UserRepository;
import CLI.Message;

import java.io.IOException;

public class Subscribe extends Command {

    @Override
    public String execute(Message message, UserRepository userRepository) throws IOException {
        User user = userRepository.getUser(message.userId);
        if (user != null && user.isSubscribed) {
            return user.name + ", Вы уже подписаны! \uD83D\uDE0F";
        } else {
            user.isSubscribed = true;
            userRepository.updateUser(user);
            return user.name + ", Спасибо за подписку! \uD83D\uDE0C";
        }
    }
}
