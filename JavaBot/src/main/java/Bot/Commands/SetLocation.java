package Bot.Commands;

import Bot.Models.States;
import Bot.Models.User;
import Bot.UserRepository;
import CLI.Message;

import java.io.IOException;

public class SetLocation extends Command{

    @Override
    public String execute(Message message, UserRepository userRepository) throws IOException {
        User user = userRepository.getUser(message.userId);
        if (user.state == States.DEFAULT) {
            user.state = States.CHANGELOCATION;
            userRepository.updateUser(user);
            return "Напишите ваш город на английском или прикрепите геолокацию";
        }

        if (message.location == null)
            user.location = message.text;
        else {
            user.lat = message.location.getLatitude();
            user.lon = message.location.getLongitude();
        }
        user.state = States.DEFAULT;
        userRepository.updateUser(user);
        return user.name + ", мы обновили вашу локацию";
    }
}
