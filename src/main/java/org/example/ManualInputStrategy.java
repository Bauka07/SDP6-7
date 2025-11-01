package org.example;

public class ManualInputStrategy implements UpdateStrategy {
    @Override
    public WeatherData fetchWeatherData(String input) {
        try {
            String[] parts = input.split(",");

            if (parts.length < 4) {
                System.out.println("Invalid input format. Using default values.");
                return new WeatherData(20.0, 50.0, 1013.0, "Manual Entry");
            }

            double temperature = Double.parseDouble(parts[0].trim());
            double humidity = Double.parseDouble(parts[1].trim());
            double pressure = Double.parseDouble(parts[2].trim());
            String description = parts[3].trim();

            System.out.println("Manual data entry processed successfully.");

            return new WeatherData(temperature, humidity, pressure, description, "Manual Input");

        } catch (Exception e) {
            System.out.println("Error parsing manual input: " + e.getMessage());
            return null;
        }
    }
}