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
            return "Напишите ваш город или прикрепите геолокацию";
        }

        if (message.location == null)
            user.location = message.text;
        else {
            user.lat = message.location.getLatitude();
            user.lon = message.location.getLongitude();
        }

        String result = user.state == States.CHANGELOCATION ? user.name + ", мы обновили вашу локацию" + "\n" : "";
        result += new Weather().execute(message, userRepository);
//        user.state = user.state == States.CHANGELOCATION ? States.DEFAULT : user.state;

        if (user.state == States.CHANGELOCATION)
            userRepository.updateUser(user);

        user.state = States.DEFAULT;
        return result;
    }
}
