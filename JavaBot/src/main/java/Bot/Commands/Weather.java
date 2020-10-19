package Bot.Commands;

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

//    private String apiBase = "http://api.openweathermap.org/data/2.5/weather?q=";
//    private String apiLatLon = "http://api.openweathermap.org/data/2.5/onecall?";
    private String apiForecast = "http://api.openweathermap.org/data/2.5/forecast?";
    private String units = "metric";
    private String lang = "en";
    private String apiKey = "99e220dcfc77677fd0106e55fbb088fe";

    @Override
    public String execute(Message message, UserRepository manager) throws IOException {
        User user = manager.getUser(message.userId);
        int today = 1;
        if (user.location != null || user.lat != 0.0f && user.lon != 0.0f) {
            try {
                WeatherApiJSON json = user.lat == 0.0f && user.lon == 0.0f
                        ? fetchForecast(user.location, today)
                        : fetchForecast(user.lat, user.lon, today);

                user.location = user.lat != 0.0f && user.lon != 0.0f ? json.city : user.location;
                user.lat = 0.0f;
                user.lon = 0.0f;
                return "Погода в " + user.location + " : " + json.temperature + "\u00B0C";
            } catch (IllegalArgumentException e) {
                user.state = 1;
                manager.updateUser(user);
                return "Вы вводили неправильный город\nНапиши свою локацию";
            } catch (Exception e) {
                e.printStackTrace();
                return "Ой, что-то пошло не так! Попробуйте в другой раз";
            }
        }
        else {
            user.state = 1;
            manager.updateUser(user);
            return "Мы не знаем где вы живете\nНапиши свою локацию";
        }
    }

    public WeatherApiJSON fetchForecast(String location, int dayIndex) throws IOException, JSONException {
        WeatherApiJSON jsonObj = null;
        try {
            jsonObj = fetch(location, dayIndex);
            if (!jsonObj.isGoodCode())
                throw new JSONException("Ой, что-то пошло не так " + jsonObj.get("message"));
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        return jsonObj;
    }

    public WeatherApiJSON fetchForecast(float lat, float lon, int dayIndex) throws IOException, JSONException {
        WeatherApiJSON jsonObj = null;
        try {
            jsonObj = fetch(lat, lon, dayIndex);
            if (!jsonObj.isGoodCode())
                throw new JSONException("Ой, что-то пошло не так " + jsonObj.get("message"));
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        return jsonObj;
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
            throw new IllegalArgumentException();

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
