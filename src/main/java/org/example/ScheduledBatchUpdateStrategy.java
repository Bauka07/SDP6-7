package org.example;

public class ScheduledBatchUpdateStrategy implements UpdateStrategy {
    private int batchCount = 0;

    @Override
    public WeatherData fetchWeatherData(String input) {
        batchCount++;
        System.out.println("Executing scheduled batch update #" + batchCount);
        System.out.println("Simulating data collection from multiple sensors...");

        try {
            Thread.sleep(1000); // Simulate delay
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // Simulate batch processing
        double avgTemp = 18 + (Math.random() * 10);
        double avgHumidity = 50 + (Math.random() * 30);
        double avgPressure = 1000 + (Math.random() * 40);

        return new WeatherData(
                avgTemp,
                avgHumidity,
                avgPressure,
                "Batch Update Data",
                "Multiple Sensors"
        );
    }
}