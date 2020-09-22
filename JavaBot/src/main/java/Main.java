import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.exceptions.TelegramApiException;

public class Main {

    public static void main(String[] args) {

        Bot bot = new Bot();

        // Initialize Telegram Provider
        // Initialize Api Context
        ApiContextInitializer.init();

        // Instantiate Telegram Bots API
        TelegramBotsApi botsApi = new TelegramBotsApi();

        // Register our bot on Telegram
        try {
            botsApi.registerBot(new TelegramProvider(bot));
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

        // Making CLI provider
        CLIApi cliApi = new CLIApi();

        CLIProvider cli = new CLIProvider(bot);
        try {
//            cliApi.registerBot(cli);
            cli.onUpdateReceived();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}