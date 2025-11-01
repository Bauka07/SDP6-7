package org.example;

public class APIUpdateStrategy implements UpdateStrategy {
    private static String apiKey = "";
    private WeatherAPIFacade apiFacade;

    public APIUpdateStrategy() {
        this.apiFacade = new WeatherAPIFacade();
    }

    public static void setApiKey(String key) {
        apiKey = key;
    }

    @Override
    public WeatherData fetchWeatherData(String cityName) {
        if (apiKey.isEmpty()) {
            System.out.println("⚠ No API key provided. Using simulated data.");
            return createSimulatedData(cityName);
        }

        try {
            return apiFacade.getWeatherForCity(cityName, apiKey);
        } catch (Exception e) {
            System.out.println("⚠ API call failed: " + e.getMessage());
            System.out.println("Using simulated data instead.");
            return createSimulatedData(cityName);
        }
    }

    private WeatherData createSimulatedData(String city) {
        double temp = 15 + (Math.random() * 20);
        double humidity = 40 + (Math.random() * 40);
        double pressure = 980 + (Math.random() * 60);
        String[] conditions = {"Clear", "Cloudy", "Rainy", "Sunny", "Partly Cloudy"};
        String condition = conditions[(int)(Math.random() * conditions.length)];

        return new WeatherData(temp, humidity, pressure, condition, city);
    }
}