package Bot.Models;

import org.json.JSONObject;

public class WeatherApiJSON extends JSONObject {
    public String statusCode;
    private JSONObject lastDay;
    public Double temperature;
    public String city;
    public Double feelsLike;
    public Integer pressure;
    public Double windSpeed;
    public String weatherDescription;
    public String icon;

    public WeatherApiJSON(String jsonString) {
        JSONObject jsonObject = new JSONObject(jsonString);
        statusCode = jsonObject.get("message").toString();
        if (isGoodCode()) {
            lastDay = jsonObject.getJSONArray("list").getJSONObject(0);
            temperature = Double.parseDouble(lastDay.getJSONObject("main").get("temp").toString());
            city = jsonObject.getJSONObject("city").get("name").toString();
            feelsLike = Double.parseDouble(lastDay.getJSONObject("main").get("feels_like").toString());
            pressure = Integer.parseInt(lastDay.getJSONObject("main").get("pressure").toString());
            windSpeed = Double.parseDouble(lastDay.getJSONObject("wind").get("speed").toString());
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
}
