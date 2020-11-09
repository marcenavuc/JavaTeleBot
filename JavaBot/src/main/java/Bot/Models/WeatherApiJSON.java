package Bot.Models;

import Bot.Commands.Command;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class WeatherApiJSON extends JSONObject {
    public String statusCode;
    private JSONObject lastDay;
    public Double temperature;
    public String city;
    public Double feelsLike;
    public String pressure;
    public Double windSpeed;
    public String weatherDescription;
    public String icon;

    public WeatherApiJSON(String jsonString) {
        JSONObject jsonObject = new JSONObject(jsonString);
        statusCode = jsonObject.get("message").toString();
        if (isGoodCode()) {
            lastDay = jsonObject.getJSONArray("list").getJSONObject(0);
            temperature = (Double) lastDay.getJSONObject("main").get("temp");
            city = jsonObject.getJSONObject("city").get("name").toString();
            feelsLike = (Double) lastDay.getJSONObject("main").get("feels_like");
            pressure = lastDay.getJSONObject("main").get("pressure").toString();
            windSpeed = (Double) lastDay.getJSONObject("wind").get("speed");
            String iconName = lastDay.getJSONArray("weather")
                    .getJSONObject(0)
                    .get("icon").toString();

            icon = new Icon().icons.get(iconName);    // iconMapper(iconName);

            weatherDescription = lastDay.getJSONArray("weather")
                    .getJSONObject(0)
                    .get("description").toString() + icon +  ":\n" +  getSuggest(temperature, windSpeed, icon);

        }
    }
    public String getSuggest(Double temperature, Double windSpeed, String icon){
        String answer = "";
        if (windSpeed >= 5.0)
            return answer + " Сильный ветер, оденьтесь соответствующе;";
        if (temperature >= 0 && temperature <=9)
            return answer + " Холодно, наденьте куртку!;";
        if (temperature >=-1 && temperature <=-9)
            return answer + " Минусовая температура, наденьте теплые вещи;";
        if (temperature >=-10 && temperature <=-19)
            return answer + " Весомый холод, не промерзайте, обязательна шапка и перчатки!";
        if (temperature >=-20 && temperature <=-45)
            return answer + " Только самые стойкие отважутся выйти на улицу!";
        //TODO: manipulation with icon
        return answer;
    }
    public boolean isGoodCode() {
        return statusCode.equals("0");
    }

   // private String iconMapper(String iconName) {

//        Map<String, String> mapper = new HashMap<>();
//            mapper.put("Clear", "☀");
//            mapper.put("Rain", "☔");
//            mapper.put("Snow", "❄");
//            mapper.put("Clouds", "☁");
//            String result = mapper.get(weatherDescription);
//            return isNull(result) ? "" : result;

//        ArrayList<String> mapper = new ArrayList<String>();
//        mapper.add("\u2600\uFE0F");
//        mapper.add("\uD83C\uDF24");
//        mapper.add("\u2601\uFE0F");
//        mapper.add("\uD83C\uDF2B");
//        mapper.add("\uD83C\uDF27");
//        mapper.add("\uD83C\uDF27");
//        String result = mapper.get(Integer.parseInt(iconName.substring(0, 1)));
//        return isNull(result) ? "" : result;
  //  }
}
