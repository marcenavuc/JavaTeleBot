package Bot.Commands;

import Bot.UserRepository;
import CLI.Message;

public class Help extends Command {

    @Override
    public String execute(Message message, UserRepository userRepository) {
        String answer = "Я умею давать прогноз погоды \uD83C\uDF24 в запрашиваемом регионе;\n";
        answer += "Для того, чтобы узнать прогноз погоды, напишите название интересующего Вас города на английском языке\n";
        answer += "или же отправьте Вашу геолокацию;\n";
        answer += "А также ты можешь подписаться на обновления;\n";
        answer += "Описание команд: \n";
        // TO DO: Поставить названия комманд автоматически
        answer += "/weather - \uD83C\uDF24 Введите, чтобы получить прогноз погоды\n";
        answer += "/change - \uD83D\uDEA9 Введите, чтобы изменить Вашу локацию\n";
        answer += "/help - \u2753 справка о том, что я умею. Ты её сейчас читаешь \uD83D\uDE1B \uD83D\uDE1B \uD83D\uDE1B\n";
        answer += "/subscribe - \uD83D\uDCDD Введите, чтобы подписаться на уведомления\n";
        answer += "/unsubscribe - Введите, чтобы отписаться от уведомлений";

        return answer;
    }
}
