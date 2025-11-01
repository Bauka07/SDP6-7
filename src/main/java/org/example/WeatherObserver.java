package org.example;

public interface WeatherObserver {
    void update(WeatherData weatherData);
    String getName();
}