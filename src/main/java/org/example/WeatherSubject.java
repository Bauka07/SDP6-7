package org.example;

import java.util.List;

public interface WeatherSubject {
    void registerObserver(WeatherObserver observer);
    void removeObserver(WeatherObserver observer);
    void notifyObservers();
    List<WeatherObserver> getObservers();
}