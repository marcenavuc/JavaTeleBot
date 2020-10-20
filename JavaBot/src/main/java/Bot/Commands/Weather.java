package Bot.Commands;

import Bot.Models.States;
import Bot.Models.User;
import Bot.Models.WeatherApiJSON;
import Bot.UserRepository;
import CLI.Message;
import org.json.JSONException;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class Weather extends Command {

    private String apiForecast = "http://api.openweathermap.org/data/2.5/forecast?";
    private String units = "metric";
    private String lang = "en";
    private String apiKey;

    public Weather(String apiKey) {
        this.apiKey = apiKey;
    }

    @Override
    public String execute(Message message, UserRepository userRepository) throws IOException {
        User user = userRepository.getUser(message.userId);
        int today = 1;
        if (user.location != null || user.isLatLonChanged()) {
                WeatherApiJSON json = user.isLatLonChanged()
                        ? fetch(user.lat, user.lon, today)
                        : fetch(user.location, today);

                if (json == null) {
                    user.state = States.CHANGELOCATION;
                    userRepository.updateUser(user);
                    return "Вы вводили неправильный город\nНапиши свою локацию";
                }

                user.location = user.isLatLonChanged() ? json.city : user.location;
                user.setLatLonToDefault();
                return "Погода в " + user.location + " : " + json.temperature + "\u00B0C";
        }
        else {
            user.state = States.CHANGELOCATION;
            userRepository.updateUser(user);
            return "Мы не знаем где вы живете\nНапиши свою локацию";
        }
    }

    private WeatherApiJSON fetch(String location, int nbDay) throws IOException, JSONException {
        String apiUrl = apiForecast + "q=" + URLEncoder.encode(location, "utf-8") + "&appid=" + apiKey + "&mode=json&units=" + units + "&lang=" + lang + "&cnt=" + nbDay;
        return getResponse(apiUrl);
    }

    private WeatherApiJSON fetch(float lat, float lon, int nbDay) throws IOException, JSONException {
        String apiUrl = apiForecast + "lat=" + Float.toString(lat) + "&lon=" + Float.toString(lon)+ "&appid=" + apiKey + "&mode=json&units=" + units + "&lang=" + lang + "&cnt=" + nbDay;
        System.out.println(apiUrl);
        return getResponse(apiUrl);
    }

    private WeatherApiJSON getResponse(String url) throws IOException {
        URL urlObject = new URL(url);

        HttpURLConnection connection = (HttpURLConnection) urlObject.openConnection();

        connection.setRequestMethod("GET");

        if (connection.getResponseCode() == 404)
            return null;

        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        return new WeatherApiJSON(response.toString());
    }

}
