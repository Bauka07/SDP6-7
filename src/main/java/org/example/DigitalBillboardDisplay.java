package org.example;

public class DigitalBillboardDisplay extends WeatherDisplay {

    public DigitalBillboardDisplay(String name) {
        super(name);
    }

    @Override
    public void update(WeatherData weatherData) {
        System.out.println("\n📺 [BILLBOARD: " + name + "]");
        System.out.println("   ╔═══════════════════════════════════════════╗");
        System.out.println("   ║                                           ║");
        System.out.println("   ║        " + String.format("%-33s", weatherData.getLocation().toUpperCase()) + "║");
        System.out.println("   ║        " + String.format("%-33s",
                formatTemperature(weatherData.getTemperature()) + " - " +
                        weatherData.getDescription().toUpperCase()) + "║");
        System.out.println("   ║                                           ║");
        System.out.println("   ╚═══════════════════════════════════════════╝");
    }
}