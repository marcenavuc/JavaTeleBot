package Bot.Models;

import org.json.JSONObject;

public class WeatherApiJSON extends JSONObject {
    public int statusCode;
    private JSONObject lastDay;
    public String temperature;
    public String city;

    public WeatherApiJSON(String jsonString) {
        JSONObject jsonObject = new JSONObject(jsonString);
        statusCode = Integer.parseInt(jsonObject.get("message").toString());
        lastDay = jsonObject.getJSONArray("list").getJSONObject(0);
        temperature = lastDay.getJSONObject("main").get("temp").toString();
        city = jsonObject.getJSONObject("city").get("name").toString();
    }

    public boolean isGoodCode() {
        return statusCode == 0;
    }
}
