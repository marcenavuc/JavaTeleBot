package Bot.Commands;

import Bot.Models.User;
import Bot.Repository;
import CLI.Message;

import java.io.IOException;

public class SetLocation extends Command{

    @Override
    public String execute(Message message, Repository manager) throws IOException {
        User user = manager.getUser(message.userId);
        if (user.state == 0) {
            user.state = 1;
            manager.updateUser(user);
            return "Напиши, свою локацию";
        }

        user.location = message.text;
        user.state = 0;
        manager.updateUser(user);
        return user.name + ", Мы обновили вашу локацию";
    }
}
