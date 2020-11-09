package Bot.Models;

import Bot.Commands.Command;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class WeatherApiJSON extends JSONObject {
    public String statusCode;
    private JSONObject lastDay;
    public String temperature;
    public String city;
    public String feelsLike;
    public String pressure;
    public String windSpeed;
    public String weatherDescription;
    public String icon;

    public WeatherApiJSON(String jsonString) {
        JSONObject jsonObject = new JSONObject(jsonString);
        statusCode = jsonObject.get("message").toString();
        if (isGoodCode()) {
            lastDay = jsonObject.getJSONArray("list").getJSONObject(0);
            temperature = lastDay.getJSONObject("main").get("temp").toString();
            city = jsonObject.getJSONObject("city").get("name").toString();
            feelsLike = lastDay.getJSONObject("main").get("feels_like").toString();
            pressure = lastDay.getJSONObject("main").get("pressure").toString();
            windSpeed = lastDay.getJSONObject("wind").get("speed").toString();
            weatherDescription = lastDay.getJSONArray("weather")
                    .getJSONObject(0)
                    .get("description").toString();

            String iconName = lastDay.getJSONArray("weather")
                    .getJSONObject(0)
                    .get("icon").toString();

            icon = iconMapper(iconName);
        }
    }

    public boolean isGoodCode() {
        return statusCode.equals("0");
    }

    private String iconMapper(String iconName) {
        ArrayList<String> mapper = new ArrayList<String>();
        mapper.add("\u2600");
        mapper.add("\u1f324");
        mapper.add("\u2601");
        mapper.add("\u1f32b");
        mapper.add("\u1f327");
        mapper.add("\u1f327");
        String result = mapper.get(Integer.parseInt(iconName.substring(0, 1)));
        return isNull(result) ? "" : result;
    }
}
