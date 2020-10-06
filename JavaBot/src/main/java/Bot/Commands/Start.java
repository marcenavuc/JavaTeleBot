package Bot.Commands;

import Bot.Repository;
import CLI.Message;

public class Start extends Command {

    @Override
    public String execute(Message message, Repository manager) {
        String answer = "Привет, меня зовут бот QQuestionBot\n";
        return answer + new Help().execute(message, manager);
    }
}

