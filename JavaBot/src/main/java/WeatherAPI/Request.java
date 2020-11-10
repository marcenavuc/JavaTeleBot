package WeatherAPI;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;


public class Request {
    private static String apiForecast = "http://api.openweathermap.org/data/2.5/forecast?";
    private static String units = "metric";
    private static String lang = "ru";
    private static String apiKey = System.getenv().get("WEATHERTOKEN");

    public static WeatherApiJSON fetch(String location, int nbDay) throws IOException, JSONException {
        String apiUrl = apiForecast + "q=" + URLEncoder.encode(location, "utf-8") + "&appid=" + apiKey + "&mode=json&units=" + units + "&lang=" + lang + "&cnt=" + nbDay;
        System.out.println(apiUrl);
        return getResponse(apiUrl);
    }

    public static WeatherApiJSON fetch(float lat, float lon, int nbDay) throws IOException, JSONException {
        String apiUrl = apiForecast + "lat=" + Float.toString(lat) + "&lon=" + Float.toString(lon)+ "&appid=" + apiKey + "&mode=json&units=" + units + "&lang=" + lang + "&cnt=" + nbDay;
        System.out.println(apiUrl);
        return getResponse(apiUrl);
    }

    private static WeatherApiJSON getResponse(String url) throws IOException {
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
