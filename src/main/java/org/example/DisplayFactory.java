package org.example;

public class DisplayFactory {

    public static final int PHONE_DISPLAY = 1;
    public static final int DESKTOP_DISPLAY = 2;
    public static final int BILLBOARD_DISPLAY = 3;

    public WeatherDisplay createDisplay(int type, String name) {
        switch (type) {
            case PHONE_DISPLAY:
                System.out.println("Creating Phone Display...");
                return new PhoneDisplay(name);

            case DESKTOP_DISPLAY:
                System.out.println("Creating Desktop App Display...");
                return new DesktopAppDisplay(name);

            case BILLBOARD_DISPLAY:
                System.out.println("Creating Digital Billboard Display...");
                return new DigitalBillboardDisplay(name);

            default:
                System.out.println("Invalid display type: " + type);
                return null;
        }
    }

    public WeatherDisplay createDisplayByName(String typeName, String displayName) {
        switch (typeName.toLowerCase()) {
            case "phone":
                return new PhoneDisplay(displayName);

            case "desktop":
                return new DesktopAppDisplay(displayName);

            case "billboard":
                return new DigitalBillboardDisplay(displayName);

            default:
                System.out.println("Unknown display type: " + typeName);
                return null;
        }
    }

    public void printAvailableTypes() {
        System.out.println("Available Display Types:");
        System.out.println("1. Phone Display - Compact mobile view");
        System.out.println("2. Desktop App Display - Detailed desktop view");
        System.out.println("3. Digital Billboard Display - Large format public display");
    }
}