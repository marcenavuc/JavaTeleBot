package Bot.Commands;

import Bot.Models.User;
import Bot.UserManager;
import CLI.CLIUpdate;

public class Subscribe extends Command {

    @Override
    public String execute(CLIUpdate update, UserManager manager) {
        User user = manager.getUser(update.userId);
        if (user != null && user.isSubscribed) {
            return user.name + ", Вы уже подписаны!";
        } else {
            if (user == null) {
                user = new User(update.userId, update.user, null, false);
                manager.addUser(user);
            }

            user.isSubscribed = true;
            manager.updateUser(user);
            return user.name + ", Спасибо! за подписку";
        }
    }
}
