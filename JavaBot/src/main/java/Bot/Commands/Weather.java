package Bot.Commands;

import Bot.Models.States;
import Bot.Models.User;
import WeatherAPI.WeatherApiJSON;
import Bot.UserRepository;
import CLI.Message;
import WeatherAPI.Request;

import java.io.IOException;

public class Weather extends Command {

    @Override
    public String execute(Message message, UserRepository userRepository) throws IOException {
        User user = userRepository.getUser(message.userId);
        int today = 1;
        if (user.location != null || user.isLatLonChanged()) {
                WeatherApiJSON json = user.isLatLonChanged()
                        ? Request.fetch(user.lat, user.lon, today)
                        : Request.fetch(user.location, today);

                if (json == null) {
                    user.state = States.CHANGELOCATION;
                    userRepository.updateUser(user);
                    return "Вы вводили неправильный город\nНапиши свою локацию";
                }

                user.location = user.isLatLonChanged() ? json.city : user.location;
                user.setLatLonToDefault();
                return String.format("В %s %s \n\uD83C\uDF21Температура: %s\u00B0C \n\uD83D\uDCA8Скорость ветра: %s м/с\nДавление: %s мбар\nОщущается как: %s\u00B0C\n",
                        user.location, json.weatherDescription,
                        Math.round(json.temperature), json.windSpeed,
                        json.pressure, Math.round(json.feelsLike));
        }
        else {
            user.state = States.CHANGELOCATION;
            userRepository.updateUser(user);
            return "Мы не знаем где вы живете\nНапиши свою локацию";
        }
    }
}
