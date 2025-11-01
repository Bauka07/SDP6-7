package org.example;

import java.util.ArrayList;
import java.util.List;

public class WeatherStation implements WeatherSubject {
    private List<WeatherObserver> observers;
    private WeatherData currentWeather;
    private UpdateStrategy updateStrategy;

    public WeatherStation() {
        this.observers = new ArrayList<>();
    }

    @Override
    public void registerObserver(WeatherObserver observer) {
        if (!observers.contains(observer)) {
            observers.add(observer);
            System.out.println("Observer registered: " + observer.getName());
        }
    }

    @Override
    public void removeObserver(WeatherObserver observer) {
        if (observers.remove(observer)) {
            System.out.println("Observer removed: " + observer.getName());
        }
    }

    public boolean removeObserverByName(String name) {
        for (WeatherObserver observer : observers) {
            if (observer.getName().equalsIgnoreCase(name)) {
                removeObserver(observer);
                return true;
            }
        }
        return false;
    }

    @Override
    public void notifyObservers() {
        System.out.println("\n--- Notifying All Observers ---");
        for (WeatherObserver observer : observers) {
            observer.update(currentWeather);
        }
        System.out.println("--- End of Notifications ---\n");
    }

    @Override
    public List<WeatherObserver> getObservers() {
        // Return a copy to prevent external modification
        return new ArrayList<>(observers);
    }

    public void setUpdateStrategy(UpdateStrategy strategy) {
        this.updateStrategy = strategy;
        System.out.println("Update strategy changed to: " + strategy.getClass().getSimpleName());
    }

    public UpdateStrategy getCurrentStrategy() {
        return updateStrategy;
    }

    public void fetchWeatherUpdate(String input) {
        if (updateStrategy == null) {
            System.out.println("Error: No update strategy set!");
            return;
        }

        System.out.println("\nFetching weather data using " +
                updateStrategy.getClass().getSimpleName() + "...");

        WeatherData newData = updateStrategy.fetchWeatherData(input);

        if (newData != null) {
            this.currentWeather = newData;
            System.out.println("✓ Weather data updated successfully!");
            notifyObservers();
        } else {
            System.out.println("✗ Failed to fetch weather data.");
        }
    }

    public WeatherData getCurrentWeather() {
        return currentWeather;
    }
}