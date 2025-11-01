package org.example;

public abstract class DisplayDecorator extends WeatherDisplay {
    protected WeatherDisplay wrappedDisplay;

    public DisplayDecorator(WeatherDisplay display) {
        super(display.getName());
        this.wrappedDisplay = display;
    }

    @Override
    public void update(WeatherData weatherData) {
        wrappedDisplay.update(weatherData);
    }
}