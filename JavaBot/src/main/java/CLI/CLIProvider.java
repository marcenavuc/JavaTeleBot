package CLI;

import Bot.Bot;

public class CLIProvider {

    private final Bot bot;

    public CLIProvider(Bot newBot) {
        bot = newBot;
        onUpdateReceived(new Message("/start"));
    }

    public void onUpdateReceived(Message message) {
        String answer = getAnswer(message);
        System.out.println(answer);
    }

    public String getAnswer(Message update) {
        return bot.takeAnswer(update);
    }

}
