package Bot.Commands;

import Bot.Models.User;
import Bot.UserManager;
import CLI.CLIUpdate;

public class Unsubscribe extends Command {

    @Override
    public String execute(CLIUpdate update, UserManager manager) {
        User user = manager.getUser(update.userId);
        if (user != null && !user.isSubscribed) {
            return user.name + ", Вы eще не подписаны!";
        } else {
            if (user == null) {
                user = new User(update.userId, update.user, null, false);
                manager.addUser(user);
            }

            user.isSubscribed = false;
            manager.updateUser(user);
            return user.name + ", Вы отписаны";
        }
    }
}