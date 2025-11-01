package org.example;

public class PhoneDisplay extends WeatherDisplay {

    public PhoneDisplay(String name) {
        super(name);
    }

    @Override
    public void update(WeatherData weatherData) {
        System.out.println("\n📱 [PHONE: " + name + "]");
        System.out.println("   🌡️  " + formatTemperature(weatherData.getTemperature()));
        System.out.println("   💧 " + String.format("%.0f%%", weatherData.getHumidity()));
        System.out.println("   📍 " + weatherData.getLocation());
        System.out.println("   " + weatherData.getDescription());
    }
}