package data;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import model.Place;
import model.Weather;
import util.Utils;

/**
 * Created by bu1b0b on 22.12.2016.
 */

public class JSONWeatherParser {

    public static Weather getWeather(String data) {
        Weather weather = new Weather();

        try {
            JSONObject jsonObject = new JSONObject(data);

            Place place = new Place();
            JSONObject coordObj = Utils.getObject("coord", jsonObject);
            place.setLat(Utils.getFloat("lat", coordObj));
            place.setLon(Utils.getFloat("lon", coordObj));

            JSONObject sysObj = Utils.getObject("sys", jsonObject);
            place.setCountry(Utils.getString("country", sysObj));
            place.setLastUpdate(Utils.getInt("dt", jsonObject));
            place.setSunrise(Utils.getInt("sunrise", sysObj));
            place.setSunset(Utils.getInt("sunset", sysObj));
            place.setCity(Utils.getString("name", jsonObject));
            weather.place = place;

            JSONArray jsonArray = jsonObject.getJSONArray("weather");
            JSONObject jsonWeather = jsonArray.getJSONObject(0);
            weather.currentCondition.setWeatherId(Utils.getInt("id", jsonWeather));
            weather.currentCondition.setDescription(Utils.getString("description", jsonWeather));
            weather.currentCondition.setCondition(Utils.getString("main", jsonWeather));
            weather.currentCondition.setIcon(Utils.getString("icon", jsonWeather));

            JSONObject tempObj = Utils.getObject("main", jsonObject);
            weather.currentCondition.setMaxTemp(Utils.getFloat("temp_max", tempObj));
            weather.currentCondition.setMinTemp(Utils.getFloat("temp_min", tempObj));
            weather.currentCondition.setTemperature(Utils.getFloat("temp", tempObj));
            weather.currentCondition.setPressure(Utils.getFloat("pressure", tempObj));
            weather.currentCondition.setHumidity(Utils.getFloat("humidity", tempObj));

            JSONObject windObj = Utils.getObject("wind", jsonObject);
            weather.wind.setSpeed(Utils.getFloat("speed", windObj));
            weather.wind.setDeg(Utils.getFloat("deg", windObj));

            JSONObject cloudObj = Utils.getObject("clouds", jsonObject);
            weather.clouds.setPrecipitation(Utils.getInt("all", cloudObj));

            return weather;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

    }

}
