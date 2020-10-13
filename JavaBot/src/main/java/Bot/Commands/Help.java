package Bot.Commands;

import Bot.Repository;
import CLI.Message;

public class Help extends Command {

    @Override
    public String execute(Message message, Repository manager) {
        String answer = "Я бот JavaWeatherBot. Я умею давать прогноз погоды в твоем регионе\n";
        answer += "Для того, чтобы узнать прогноз погоды просто напиши название своего города\n";
        answer += "Или же отправь свою геолокацию\n";
        answer += "А также ты можешь подписаться на обновления\n";
        answer += "Описание комманд: \n";
        // TO DO: Поставить названия комманд автоматически
        answer += "/weather - Введите, чтобы получить прогноз погоды\n";
        answer += "/change - Введите, чтобы изменить свою локацию\n";
        answer += "/help - Выводит справку о том, что я умею. Ты ее сейчас считаешь)))\n";
        answer += "/subscribe - Введите, чтобы подписаться на обновления\n";
        answer += "/unsubscribe - Введите, чтобы отписаться от уведомлений";

        return answer;
    }
}
