package Bot.Commands;

import Bot.UserManager;
import CLI.CLIUpdate;

public class Start extends Command {

    @Override
    public String execute(CLIUpdate update, UserManager manager) {
        String answer = "Привет, меня зовут бот QQuestionBot\n";
        return answer + new Help().execute(update, manager);
    }
}

