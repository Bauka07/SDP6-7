package org.example;

public class StatisticsDecorator extends DisplayDecorator {
    private double minTemp = Double.MAX_VALUE;
    private double maxTemp = Double.MIN_VALUE;
    private double totalTemp = 0;
    private int count = 0;

    public StatisticsDecorator(WeatherDisplay display) {
        super(display);
    }

    @Override
    public void update(WeatherData weatherData) {
        // display the normal update
        wrappedDisplay.update(weatherData);

        // then add the statistics functionality
        updateStatistics(weatherData);
        displayStatistics();
    }

    private void updateStatistics(WeatherData weatherData) {
        double temp = weatherData.getTemperature();

        if (temp < minTemp) {
            minTemp = temp;
        }
        if (temp > maxTemp) {
            maxTemp = temp;
        }

        totalTemp += temp;
        count++;
    }

    private void displayStatistics() {
        double avgTemp = totalTemp / count;

        System.out.println("   📊 Statistics (from " + count + " updates):");
        System.out.println("      Min: " + String.format("%.1f°C", minTemp) +
                " | Max: " + String.format("%.1f°C", maxTemp) +
                " | Avg: " + String.format("%.1f°C", avgTemp));
    }

    @Override
    public String getName() {
        return wrappedDisplay.getName() + " [Statistics]";
    }

    public void resetStatistics() {
        minTemp = Double.MAX_VALUE;
        maxTemp = Double.MIN_VALUE;
        totalTemp = 0;
        count = 0;
        System.out.println("Statistics reset for: " + wrappedDisplay.getName());
    }
}