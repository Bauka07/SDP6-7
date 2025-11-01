package org.example;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;

public class WeatherAPIFacade {
    private static final String API_BASE_URL = "https://api.openweathermap.org/data/2.5/weather";

    public WeatherData getWeatherForCity(String cityName, String apiKey) throws Exception {
        String urlString = buildURL(cityName, apiKey);
        String jsonResponse = makeHTTPRequest(urlString);
        return parseWeatherData(jsonResponse, cityName);
    }

    private String buildURL(String cityName, String apiKey) {
        return String.format("%s?q=%s&appid=%s&units=metric",
                API_BASE_URL, cityName, apiKey);
    }

    private String makeHTTPRequest(String urlString) throws Exception {
        URL url = new URL(urlString);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setConnectTimeout(5000);
        conn.setReadTimeout(5000);

        int responseCode = conn.getResponseCode();

        if (responseCode != 200) {
            throw new Exception("HTTP Error: " + responseCode);
        }

        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        conn.disconnect();

        return response.toString();
    }

    private WeatherData parseWeatherData(String jsonResponse, String cityName) {
        JSONObject json = new JSONObject(jsonResponse);

        JSONObject main = json.getJSONObject("main");
        double temperature = main.getDouble("temp");
        double humidity = main.getDouble("humidity");
        double pressure = main.getDouble("pressure");

        String description = json.getJSONArray("weather")
                .getJSONObject(0)
                .getString("description");

        return new WeatherData(temperature, humidity, pressure, description, cityName);
    }
}