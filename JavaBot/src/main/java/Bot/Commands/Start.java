package Bot.Commands;

import CLI.CLIUpdate;

public class Start extends Command {

    @Override
    public String execute(CLIUpdate update) {
        String answer = "Привет, меня зовут бот QQuestionBot\n";
        return answer + new Help().execute(update);
    }
}

