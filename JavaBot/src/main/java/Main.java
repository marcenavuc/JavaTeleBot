import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.exceptions.TelegramApiException;

public class Main {

    public static void main(String[] args) {

//        // Initialize Api Context
//        ApiContextInitializer.init();
//
//        // Instantiate Telegram Bots API
//        TelegramBotsApi botsApi = new TelegramBotsApi();
//
//        // Register our bot on Telegram
//        try {
//            botsApi.registerBot(new TelegramProvider(new Bot()));
//        } catch (TelegramApiException e) {
//            e.printStackTrace();
//        }

        // Making CLI provider
        CLIProvider cli = new CLIProvider(new Bot());
        try {
            cli.onUpdateReceived();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}