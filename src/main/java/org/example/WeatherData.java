package org.example;// WeatherData.java
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class WeatherData {
    private double temperature;
    private double humidity;
    private double pressure;
    private String description;
    private String location;
    private LocalDateTime timestamp;

    public WeatherData(double temperature, double humidity, double pressure, String description) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        this.description = description;
        this.location = "Unknown";
        this.timestamp = LocalDateTime.now();
    }

    public WeatherData(double temperature, double humidity, double pressure,
                       String description, String location) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        this.description = description;
        this.location = location;
        this.timestamp = LocalDateTime.now();
    }

    public double getTemperature() {
        return temperature;
    }

    public double getHumidity() {
        return humidity;
    }

    public double getPressure() {
        return pressure;
    }

    public String getDescription() {
        return description;
    }

    public String getLocation() {
        return location;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getFormattedTimestamp() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return timestamp.format(formatter);
    }

    @Override
    public String toString() {
        return String.format("Weather at %s: %.1f°C, Humidity: %.1f%%, Pressure: %.1f hPa - %s",
                location, temperature, humidity, pressure, description);
    }

    public String getDetailedInfo() {
        return String.format(
                "╔════════════════════════════════════════╗\n" +
                        "║         WEATHER INFORMATION            ║\n" +
                        "╠════════════════════════════════════════╣\n" +
                        "║ Location:    %-25s ║\n" +
                        "║ Temperature: %-25s ║\n" +
                        "║ Humidity:    %-25s ║\n" +
                        "║ Pressure:    %-25s ║\n" +
                        "║ Condition:   %-25s ║\n" +
                        "║ Time:        %-25s ║\n" +
                        "╚════════════════════════════════════════╝",
                location,
                String.format("%.1f°C", temperature),
                String.format("%.1f%%", humidity),
                String.format("%.1f hPa", pressure),
                description,
                getFormattedTimestamp()
        );
    }
}