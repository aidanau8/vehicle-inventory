package com.pluralsight;

import java.util.Scanner;

public class VehicleInventory {

    static Scanner scanner = new Scanner(System.in);
    static Vehicle[] vehicles = new Vehicle[20];
    static int vehicleCount = 6;

    public static void main(String[] args) {
        vehicles[0] = new Vehicle(101121, "Ford Explorer", "Red", 45000, 13500);
        vehicles[1] = new Vehicle(101122, "Toyota Camry", "Blue", 60000, 11000);
        vehicles[2] = new Vehicle(101123, "Chevrolet Malibu", "Black", 50000, 9700);
        vehicles[3] = new Vehicle(101124, "Honda Civic", "White", 70000, 7500);
        vehicles[4] = new Vehicle(101125, "Subaru Outback", "Green", 55000, 14500);
        vehicles[5] = new Vehicle(101126, "Jeep Wrangler", "Yellow", 30000, 16000);

        boolean running = true;

        while (running) {
            System.out.println("\nWhat do you want to do?");
            System.out.println("1 - List all vehicles");
            System.out.println("2 - Search by make/model");
            System.out.println("3 - Search by price range");
            System.out.println("4 - Search by color");
            System.out.println("5 - Add a vehicle");
            System.out.println("6 - Quit");

            int command = readInt("Enter your command: ");

            switch (command) {
                case 1:
                    listAllVehicles();
                    break;
                case 2:
                    findVehiclesByMakeModel();
                    break;
                case 3:
                    findVehiclesByPrice();
                    break;
                case 4:
                    findVehiclesByColor();
                    break;
                case 5:
                    addVehicle();
                    break;
                case 6:
                    running = false;
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Please enter a number from 1 to 6.");
            }
        }

        scanner.close();
    }

    public static void printVehicle(Vehicle vehicle) {
        System.out.printf(
                "%d | %s | %s | %d miles | $%.2f%n",
                vehicle.getVehicleId(),
                vehicle.getMakeModel(),
                vehicle.getColor(),
                vehicle.getOdometerReading(),
                vehicle.getPrice()
        );
    }

    public static void listAllVehicles() {
        for (int i = 0; i < vehicleCount; i++) {
            printVehicle(vehicles[i]);
        }
    }

    public static void findVehiclesByMakeModel() {
        String search = readText("Enter make/model: ");
        boolean found = false;

        for (int i = 0; i < vehicleCount; i++) {
            if (vehicles[i].getMakeModel().toLowerCase()
                    .contains(search.toLowerCase())) {
                printVehicle(vehicles[i]);
                found = true;
            }
        }

        if (!found) {
            System.out.println("No vehicles found.");
        }
    }

    public static void findVehiclesByPrice() {
        double min = readDouble("Enter minimum price: ");
        double max = readDouble("Enter maximum price: ");

        if (min > max) {
            System.out.println("Minimum price cannot exceed maximum price.");
            return;
        }

        boolean found = false;

        for (int i = 0; i < vehicleCount; i++) {
            double price = vehicles[i].getPrice();

            if (price >= min && price <= max) {
                printVehicle(vehicles[i]);
                found = true;
            }
        }

        if (!found) {
            System.out.println("No vehicles found.");
        }
    }

    public static void findVehiclesByColor() {
        String color = readText("Enter color: ");
        boolean found = false;

        for (int i = 0; i < vehicleCount; i++) {
            if (vehicles[i].getColor().equalsIgnoreCase(color)) {
                printVehicle(vehicles[i]);
                found = true;
            }
        }

        if (!found) {
            System.out.println("No vehicles found.");
        }
    }

    public static void addVehicle() {
        if (vehicleCount >= vehicles.length) {
            System.out.println("Inventory is full.");
            return;
        }

        long id = readLong("Enter vehicle ID: ");

        for (int i = 0; i < vehicleCount; i++) {
            if (vehicles[i].getVehicleId() == id) {
                System.out.println("This vehicle ID already exists.");
                return;
            }
        }

        String makeModel = readText("Enter make/model: ");
        String color = readText("Enter color: ");
        int odometer = readInt("Enter odometer reading: ");
        double price = readDouble("Enter price: ");

        vehicles[vehicleCount] =
                new Vehicle(id, makeModel, color, odometer, price);

        vehicleCount++;

        System.out.println("Vehicle added successfully!");
    }

    public static String readText(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();

            if (!input.isEmpty()) {
                return input;
            }

            System.out.println("Please enter a value.");
        }
    }

    public static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);

            try {
                int value = Integer.parseInt(scanner.nextLine().trim());

                if (value >= 0) {
                    return value;
                }
            } catch (NumberFormatException e) {
                // Ask again when the input is not an integer.
            }

            System.out.println("Please enter a non-negative whole number.");
        }
    }

    public static long readLong(String prompt) {
        while (true) {
            System.out.print(prompt);

            try {
                long value = Long.parseLong(scanner.nextLine().trim());

                if (value > 0) {
                    return value;
                }
            } catch (NumberFormatException e) {
                // Ask again when the input is not a long.
            }

            System.out.println("Please enter a positive whole number.");
        }
    }

    public static double readDouble(String prompt) {
        while (true) {
            System.out.print(prompt);

            try {
                double value = Double.parseDouble(scanner.nextLine().trim());

                if (Double.isFinite(value) && value >= 0) {
                    return value;
                }
            } catch (NumberFormatException e) {
                // Ask again when the input is not a number.
            }

            System.out.println("Please enter a non-negative number.");
        }
    }
}