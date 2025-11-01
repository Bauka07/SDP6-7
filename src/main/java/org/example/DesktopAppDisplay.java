package org.example;

public class DesktopAppDisplay extends WeatherDisplay {

    public DesktopAppDisplay(String name) {
        super(name);
    }

    @Override
    public void update(WeatherData weatherData) {
        System.out.println("\n🖥️  [DESKTOP APP: " + name + "]");
        System.out.println("   ┌─────────────────────────────────────┐");
        System.out.println("   │ Temperature: " + formatTemperature(weatherData.getTemperature()));
        System.out.println("   │ Humidity:    " + String.format("%.1f%%", weatherData.getHumidity()));
        System.out.println("   │ Pressure:    " + String.format("%.1f hPa", weatherData.getPressure()));
        System.out.println("   │ Condition:   " + weatherData.getDescription());
        System.out.println("   │ Location:    " + weatherData.getLocation());
        System.out.println("   │ Time:        " + weatherData.getFormattedTimestamp());
        System.out.println("   └─────────────────────────────────────┘");
    }
}