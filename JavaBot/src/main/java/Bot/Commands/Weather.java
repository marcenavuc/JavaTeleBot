package Bot.Commands;

import Bot.Models.User;
import Bot.Repository;
import CLI.Message;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class Weather extends Command {

    private static final long serialVersionUID = 1L;
    private String apiBase = "http://api.openweathermap.org/data/2.5/weather?q=";
    private String apiForecast = "http://api.openweathermap.org/data/2.5/forecast?q=";
    private String units = "metric";
    private String lang = "en";
    private String apiKey = "99e220dcfc77677fd0106e55fbb088fe";
    private HttpClient client;

    @Override
    public String execute(Message message, Repository manager) {
        User user = manager.getUser(message.userId);
        int today = 1;

        try {
            JSONObject json = fetchForecast(user.location, today);
            String temperature = json.getJSONObject("main").get("temp").toString();
            return "Погода в " + user.location + " : " + temperature;
        } catch (IOException e) {
//            e.printStackTrace();
            return "ой, что-то пошло не так! Попробуйте в другой раз";
        }
    }

    private String getResponse(String url) throws IOException {
        URL urlObject = new URL(url);

        HttpURLConnection connection = (HttpURLConnection) urlObject.openConnection();

        connection.setRequestMethod("GET");

        if (connection.getResponseCode() == 404) {
            throw new IllegalArgumentException();
        }

        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        return response.toString();
    }

    private JSONObject fetch(String location, int nbDay) throws ClientProtocolException, IOException, JSONException {
        String apiUrl = apiForecast + URLEncoder.encode(location, "utf-8") + "&appid=" + apiKey + "&mode=json&units=" + units + "&lang=" + lang + "&cnt=" + nbDay;
        System.out.println(apiUrl);
        String response = getResponse(apiUrl);
        JSONObject json = new JSONObject(response);
        return json;
    }

    public JSONObject fetchForecast(String location, int dayIndex) throws ClientProtocolException, IOException, JSONException {
        String[] result = new String[11];
        JSONObject json = new JSONObject();
        String localUnits = "celsius";
        JSONObject jsonObj = null;
        try {
            jsonObj = fetch(location, (dayIndex));
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

        // Getting the list node
        JSONArray list;
        try {
            list = jsonObj.getJSONArray("list");
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

            // Getting the required element from list by dayIndex
        json = list.getJSONObject(dayIndex - 1);
//            result[0] = item.getJSONArray("weather").getJSONObject(0).get("description").toString();
//            JSONObject main = item.getJSONObject("main");
//            result[1] = main.get("temp").toString();
//            result[2] = location;
//            result[3] = item.getJSONArray("weather").getJSONObject(0).get("id").toString();
//            result[4] = main.get("pressure").toString();
//            result[5] = main.get("humidity").toString();
//            result[6] = main.get("temp_min").toString();
//            result[7] = main.get("temp_max").toString();
//            JSONObject wind = item.getJSONObject("wind");
//            result[8] = wind.get("speed").toString();
//            result[9] = wind.get("deg").toString();
//            result[10] = localUnits;

        return json;
    }


}
