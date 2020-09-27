package CLI;

import Bot.Bot;

public class CLIProvider {

    private final Bot bot;

    public CLIProvider(Bot newBot) {
        bot = newBot;
        onUpdateReceived(new CLIUpdate("/start"));
    }

    public void onUpdateReceived(CLIUpdate update) {
        String answer = getAnswer(update);
        // Как-тo отправить ответ
        System.out.println(answer);
    }

    public String getAnswer(CLIUpdate update) {
        return bot.takeAnswer(update);
    }

}
