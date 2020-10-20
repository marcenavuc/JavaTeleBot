package Bot.Commands;

import Bot.UserRepository;
import CLI.Message;

public class Start extends Command {

    @Override
    public String execute(Message message, UserRepository userRepository) {
        String answer = "Привет \uD83D\uDC4B, меня зовут JavaWeatherBot\n" ;
        return answer + new Help().execute(message, userRepository);
    }
}

