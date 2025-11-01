package org.example;

public abstract class WeatherDisplay implements WeatherObserver {
    protected String name;

    public WeatherDisplay(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public abstract void update(WeatherData weatherData);

    protected String formatTemperature(double temp) {
        return String.format("%.1f°C", temp);
    }
}