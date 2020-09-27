import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.exceptions.TelegramApiException;

public class Main {

    public static void main(String[] args) {

        Bot bot = new Bot();
        TelegramProvider provider = new TelegramProvider(bot);
        // Initialize Telegram Provider
        // Initialize Api Context
        ApiContextInitializer.init();

        // Instantiate Telegram Bots API
        TelegramBotsApi botsApi = new TelegramBotsApi();

        // Register our bot on Telegram
        try {
            botsApi.registerBot(provider);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

        // Making CLI provider
        CLIApi cliApi = new CLIApi();

//        CLIProvider cli = new CLIProvider(bot);
        try {
//            cliApi.registerBot(cli);
//            cli.onUpdateReceived();
            cliApi.registerBot(provider);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}