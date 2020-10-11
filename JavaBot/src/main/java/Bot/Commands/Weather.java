package Bot.Commands;

import Bot.Repository;
import CLI.Message;

public class Weather extends Command {

    @Override
    public String execute(Message message, Repository manager) {

        return "Здесь будет функционал вывода погоды";
    }
}
