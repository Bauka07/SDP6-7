package org.example;// Main.java
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        WeatherStation weatherStation = new WeatherStation();

        // create display factory
        DisplayFactory displayFactory = new DisplayFactory();

        // initialize with API strategy
        weatherStation.setUpdateStrategy(new APIUpdateStrategy());

        System.out.println("=== Weather Notification System ===");
        System.out.println("Enter your OpenWeatherMap API key (or press Enter to use manual mode):");
        String apiKey = scanner.nextLine().trim();

        if (!apiKey.isEmpty()) {
            APIUpdateStrategy.setApiKey(apiKey);
        }

        boolean running = true;

        while (running) {
            displayMenu();

            try {
                int choice = Integer.parseInt(scanner.nextLine().trim());

                switch (choice) {
                    case 0:
                        System.out.println("Exiting Weather Notification System. Goodbye!");
                        running = false;
                        break;

                    case 1:
                        addDisplay(scanner, weatherStation, displayFactory);
                        break;

                    case 2:
                        removeDisplay(scanner, weatherStation);
                        break;

                    case 3:
                        listDisplays(weatherStation);
                        break;

                    case 4:
                        changeUpdateStrategy(scanner, weatherStation);
                        break;

                    case 5:
                        fetchWeatherData(scanner, weatherStation);
                        break;

                    case 6:
                        displayCurrentWeather(weatherStation);
                        break;

                    default:
                        System.out.println("Invalid option. Please try again.");
                }

            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }

            System.out.println();
        }

        scanner.close();
    }

    private static void displayMenu() {
        System.out.println("\n╔════════════════════════════════════════╗");
        System.out.println("║     WEATHER NOTIFICATION SYSTEM        ║");
        System.out.println("╠════════════════════════════════════════╣");
        System.out.println("║ 0. Exit                                ║");
        System.out.println("║ 1. Add Display/Subscriber              ║");
        System.out.println("║ 2. Remove Display/Subscriber           ║");
        System.out.println("║ 3. List All Displays                   ║");
        System.out.println("║ 4. Change Update Strategy              ║");
        System.out.println("║ 5. Fetch Weather Data                  ║");
        System.out.println("║ 6. Display Current Weather             ║");
        System.out.println("╚════════════════════════════════════════╝");
        System.out.print("Enter your choice: ");
    }

    private static void addDisplay(Scanner scanner, WeatherStation station, DisplayFactory factory) {
        System.out.println("\n--- Add New Display ---");
        System.out.println("1. Phone Display");
        System.out.println("2. Desktop App Display");
        System.out.println("3. Digital Billboard Display");
        System.out.print("Choose display type: ");

        int type = Integer.parseInt(scanner.nextLine().trim());
        System.out.print("Enter display name: ");
        String name = scanner.nextLine().trim();

        WeatherDisplay display = factory.createDisplay(type, name);

        if (display != null) {
            System.out.println("\nEnhance display with decorators?");
            System.out.println("1. No enhancement");
            System.out.println("2. Add Temperature Alert");
            System.out.println("3. Add Statistics Tracking");
            System.out.println("4. Add Both");
            System.out.print("Choose: ");

            int decoratorChoice = Integer.parseInt(scanner.nextLine().trim());

            switch (decoratorChoice) {
                case 2:
                    display = new TemperatureAlertDecorator(display);
                    break;
                case 3:
                    display = new StatisticsDecorator(display);
                    break;
                case 4:
                    display = new StatisticsDecorator(new TemperatureAlertDecorator(display));
                    break;
            }

            station.registerObserver(display);
            System.out.println("✓ Display added successfully!");
        } else {
            System.out.println("✗ Invalid display type.");
        }
    }

    private static void removeDisplay(Scanner scanner, WeatherStation station) {
        System.out.println("\n--- Remove Display ---");
        listDisplays(station);
        System.out.print("\nEnter display name to remove: ");
        String name = scanner.nextLine().trim();

        boolean removed = station.removeObserverByName(name);
        if (removed) {
            System.out.println("✓ Display removed successfully!");
        } else {
            System.out.println("✗ Display not found.");
        }
    }

    private static void listDisplays(WeatherStation station) {
        System.out.println("\n--- Registered Displays ---");
        java.util.List<WeatherObserver> displays = station.getObservers();

        if (displays.isEmpty()) {
            System.out.println("No displays registered.");
        } else {
            for (int i = 0; i < displays.size(); i++) {
                System.out.println((i + 1) + ". " + displays.get(i).getName());
            }
        }
    }

    private static void changeUpdateStrategy(Scanner scanner, WeatherStation station) {
        System.out.println("\n--- Change Update Strategy ---");
        System.out.println("1. API Update (Real-time from OpenWeatherMap)");
        System.out.println("2. Scheduled Batch Update (Simulated)");
        System.out.println("3. Manual Input Update");
        System.out.print("Choose strategy: ");

        int strategy = Integer.parseInt(scanner.nextLine().trim());

        switch (strategy) {
            case 1:
                station.setUpdateStrategy(new APIUpdateStrategy());
                System.out.println("✓ Strategy changed to API Update");
                break;
            case 2:
                station.setUpdateStrategy(new ScheduledBatchUpdateStrategy());
                System.out.println("✓ Strategy changed to Scheduled Batch Update");
                break;
            case 3:
                station.setUpdateStrategy(new ManualInputStrategy());
                System.out.println("✓ Strategy changed to Manual Input");
                break;
            default:
                System.out.println("✗ Invalid strategy.");
        }
    }

    private static void fetchWeatherData(Scanner scanner, WeatherStation station) {
        System.out.println("\n--- Fetch Weather Data ---");

        if (station.getCurrentStrategy() instanceof APIUpdateStrategy) {
            System.out.print("Enter city name: ");
            String city = scanner.nextLine().trim();
            station.fetchWeatherUpdate(city);
        } else if (station.getCurrentStrategy() instanceof ManualInputStrategy) {
            System.out.print("Enter temperature (°C): ");
            double temp = Double.parseDouble(scanner.nextLine().trim());
            System.out.print("Enter humidity (%): ");
            double humidity = Double.parseDouble(scanner.nextLine().trim());
            System.out.print("Enter pressure (hPa): ");
            double pressure = Double.parseDouble(scanner.nextLine().trim());
            System.out.print("Enter description: ");
            String desc = scanner.nextLine().trim();

            String input = temp + "," + humidity + "," + pressure + "," + desc;
            station.fetchWeatherUpdate(input);
        } else {
            station.fetchWeatherUpdate("");
        }
    }

    private static void displayCurrentWeather(WeatherStation station) {
        WeatherData current = station.getCurrentWeather();
        if (current != null) {
            System.out.println("\n" + current.getDetailedInfo());
        } else {
            System.out.println("\nNo weather data available. Please fetch data first.");
        }
    }
}