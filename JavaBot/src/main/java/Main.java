import Bot.Bot;
import CLI.CLIApi;
import CLI.CLIProvider;
import Schedule.Schedule;
import Telegram.TelegramProvider;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import java.util.Map;
import java.util.Timer;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        ApiContextInitializer.init();

        Map<String, String> envs = System.getenv();
        Bot bot = new Bot();

        TelegramProvider telegramProvider = new TelegramProvider(bot, envs.get("TELEGRAMTOKEN"));
        TelegramBotsApi botsApi = new TelegramBotsApi();

        Timer timer = new Timer();
        timer.schedule(new Schedule(bot, telegramProvider), 0, 1000 * 3600);  // every hour

        try {
            // Register our bot on Telegram
            botsApi.registerBot(telegramProvider);

            // Making CLI provider
            CLIApi cliApi = new CLIApi();
            CLIProvider cli = new CLIProvider(bot);
            cliApi.registerBot(cli);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}