import Bot.Bot;
import CLI.CLIApi;
import CLI.CLIProvider;
import Telegram.TelegramProvider;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import java.util.Map;

public class Main {

    public static void main(String[] args) {

        Map<String, String> envs = System.getenv();

        //Initialize bot
        Bot bot = new Bot(envs.get("WEATHERTOKEN"));

        // Initialize Telegram Provider
        // Initialize Api Context
        ApiContextInitializer.init();

        // Instantiate Telegram Bots API
        TelegramBotsApi botsApi = new TelegramBotsApi();

        // Register our bot on Telegram
        try {
            botsApi.registerBot(new TelegramProvider(bot, envs.get("TELEGRAMTOKEN")));
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

        // Making CLI provider
        CLIApi cliApi = new CLIApi();

        CLIProvider cli = new CLIProvider(bot);
        try {
            cliApi.registerBot(cli);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}