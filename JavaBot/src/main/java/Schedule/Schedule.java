package Schedule;

import Bot.Bot;
import Bot.Models.User;
import Bot.UserRepository;
import CLI.Message;
import Telegram.TelegramProvider;

import java.io.IOException;
import java.util.Date;
import java.util.TimerTask;


public class Schedule extends TimerTask {
    private int timeOfSend = 9;
    private final Bot bot;
    private TelegramProvider telegramProvider;
    public UserRepository userRepository = new UserRepository();

    public Schedule(Bot bot, TelegramProvider telegramProvider) {
        this.bot = bot;
        this.telegramProvider = telegramProvider;
    }

    public void sendToUsersBySchedule() throws IOException {
        for (int userId: userRepository.getUsers().keySet()) {
            User user = userRepository.getUser(userId);
            if (user.isSubscribed) {
                Message message = new Message("/weather");
                message.userId = userId;
                String answer = bot.takeAnswer(message);
                System.out.println(answer);
                telegramProvider.SendMessageToTelegram(user.chatId, answer);
            }
        }
    }

    @Override
    public void run() {
        try {
            if (new Date().getHours() == timeOfSend) {
                sendToUsersBySchedule();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
