package Bot.Commands;

import Bot.UserManager;
import CLI.CLIUpdate;

public class Weather extends Command {

    @Override
    public String execute(CLIUpdate update, UserManager manager) {
        return "Здесь будет функционал вывода погоды";
    }
}
