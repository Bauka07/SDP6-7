package org.example;

public class TemperatureAlertDecorator extends DisplayDecorator {
    private static final double HIGH_TEMP_THRESHOLD = 30.0;
    private static final double LOW_TEMP_THRESHOLD = 5.0;

    public TemperatureAlertDecorator(WeatherDisplay display) {
        super(display);
    }

    @Override
    public void update(WeatherData weatherData) {
        // display the normal update
        wrappedDisplay.update(weatherData);

        // then add the alert functionality
        displayTemperatureAlert(weatherData);
    }

    private void displayTemperatureAlert(WeatherData weatherData) {
        double temp = weatherData.getTemperature();

        if (temp > HIGH_TEMP_THRESHOLD) {
            System.out.println("   🔥 ALERT: High temperature warning! (" +
                    String.format("%.1f°C", temp) + ")");
            System.out.println("   ⚠️  Stay hydrated and avoid prolonged sun exposure.");
        } else if (temp < LOW_TEMP_THRESHOLD) {
            System.out.println("   ❄️  ALERT: Low temperature warning! (" +
                    String.format("%.1f°C", temp) + ")");
            System.out.println("   ⚠️  Dress warmly and protect against cold.");
        }
    }

    @Override
    public String getName() {
        return wrappedDisplay.getName() + " [Temp Alert]";
    }
}