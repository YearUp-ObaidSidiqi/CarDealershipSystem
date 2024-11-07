package com.yearup;

import com.yearup.dealership.Dealership;
import com.yearup.dealership.Vehicle;
import com.yearup.fileManager.DealershipFileManager;

import java.io.IOException;

public  class UserInterface {

     Dealership dealership;

    public UserInterface() throws IOException {
         dealership = DealershipFileManager.ReadVehiclesFromCSV("vehicles.csv");
        displayIntroMsg();
        boolean j = true;
         while (j){
             displayMainMenu();
             int i = Utilities.PromptForInt("Please select an option (1, 2, 3, 4, 5, 6, 7, 8, 9, 99): ");
             switch (i) {
                 case 1 -> System.out.println("WIP// Find vehicles within a price range");
                 case 2 -> System.out.println("WI// Find vehicles by make / model");
                 case 3 -> System.out.println("WIP// Find vehicles by year range");
                 case 4 -> System.out.println("WIP// Find vehicles by color");
                 case 5 -> System.out.println("WIP// Find vehicles by mileage range");
                 case 6 -> System.out.println("WIP// Find vehicles by type (car, truck, SUV, van)");
                 case 7 -> displayAllVehicles();
                 case 8 -> addVehicle();
                 case 9 -> removeVehicle();
                 case 99 -> {
                     System.out.println("Thank you for choosing us, Cheers Bye Bye!");
                     j = false;
                 }
             }
         }


     }

    void displayMainMenu(){
        String menu = """
                ┌───────────────────────────────────────────────────────────────────┐
                │                 Please select from the following choices:         │
                │                                                                   │
                │                1 - Find vehicles within a price range             │
                │                2 - Find vehicles by make / model                  │
                │                3 - Find vehicles by year range                    │
                │                4 - Find vehicles by color                         │
                │                5 - Find vehicles by mileage range                 │
                │                6 - Find vehicles by type (car, truck, SUV, van)   │
                │                7 - List ALL vehicles                              │
                │                8 - Add a vehicle                                  │
                │                9 - Remove a vehicle                               │
                │                10 - Sales/Lease                                   │
                │               99 - Quit                                           │
                │                                                                   │
                └───────────────────────────────────────────────────────────────────┘
                """;
        System.out.println(menu);
    }
    void displayAllVehicles(){
        System.out.println(tableHeader());
        for ( Vehicle v :dealership.getAllVehicles()){
            System.out.println(v.toString());
        }
    }
    void displayVehiclesByMake(String make){
        for ( Vehicle v :dealership.getVehiclesByMake(make)){
            System.out.println(v.toString());
        }
    }
    void displayVehiclesByModel(String model){
        for ( Vehicle v :dealership.getVehiclesByModel(model)){
            System.out.println(v.toString());
        }
    }
    void displayVehiclesByMakeModel(String make, String model){
        for ( Vehicle v :dealership.getVehicleByMakeModel(make, model)){
            System.out.println(v.toString());
        }
    }
    void displayVehiclesByType(String type){
        for ( Vehicle v :dealership.getVehiclesByType(type)){
            System.out.println(v.toString());
        }
    }
    void displayVehiclesByColor(String color){
        for ( Vehicle v :dealership.getVehiclesByColor(color)){
            System.out.println(v.toString());
        }
    }
    void displayVehiclesByYear(int min, int max){
        for ( Vehicle v :dealership.getVehiclesByYear(min, max)){
            System.out.println(v.toString());
        }
    }
    void displayVehiclesByPrice(int min, int max){
        for ( Vehicle v :dealership.getVehiclesByPrice(min, max)){
            System.out.println(v.toString());
        }
    }
    void displayIntroMsg(){
        String intro = """
                ┌───────────────────────────────────────────────────────────────────┐
                │            Welcome to the QPL Vehicle Management System           │
                │         Here, you can search, add, and manage vehicle data        │
                │       efficiently. Please select an option from the menu below.   │
                └───────────────────────────────────────────────────────────────────┘
                """;


        System.out.println(intro);
    }
    void addVehicle() {
        System.out.println("To add a new vehicle to our inventory, please provide the following information:");
        try {
            int vin = Utilities.PromptForInt("Enter the VIN (last 5 digit): ");

            int year;
            do {
                year = Utilities.PromptForInt("Enter the manufacturing year (e.g., 1951 - 2024): ");
                if (year < 1951 || year > 2025) {
                    System.out.println("Please enter a valid year.");
                }
            } while (year < 1951 || year > 2025);

            String make = Utilities.PromptForString("Enter the make (e.g., Toyota): ");
            String model = Utilities.PromptForString("Enter the model (e.g., Supra): ");
            String vehicleType = Utilities.PromptForString("Enter the vehicle type (e.g., car, truck, SUV, van): ");
            String color = Utilities.PromptForString("Enter the color: ");

            int odometer;
            do {
                odometer = Utilities.PromptForInt("Enter the odometer reading (miles): ");
                if (odometer < 0) {
                    System.out.println("Odometer reading cannot be negative.");
                }
            } while (odometer < 0);

            double price;
            do {
                price = Utilities.PromptForDouble("Enter the price (e.g., $ 65,000.00): ");
                if (price <= 0) {
                    System.out.println("Price must be a positive value.");
                }
            } while (price <= 0);
            dealership.addVehicle(vin, year, make, model, vehicleType, color, odometer, price);
            System.out.println("Vehicle successfully added to inventory!");

        } catch (Exception e) {
            System.out.println("An error occurred while adding the vehicle. Please try again." + e.getMessage());
        }
    } /// WOP change the date to local date
    void removeVehicle(){
        System.out.println("To remove a vehicle from our inventory, please provide the last five digit of the VIN number of the vehicle (e.g., '23456'):");
        int vin = Utilities.PromptForInt("Enter the VIN: ");
        if (dealership.vehicleExists(vin)){
            dealership.removeVehicle(vin);
            System.out.println("Vehicle with VIN " + vin + " has been successfully removed from the inventory.");
        } else {
            System.out.println("Error: No vehicle found with the provided VIN. Please check and try again.");
        }

    }
    String tableHeader() {
        return String.format("%-7s | %-4s | %-6s | %-11s | %-10s | %-7s | %-8s | $%-7s\n" +
                        "───────────────────────────────────────────────────────────────────────────────────────────",
                "VIN", "Year", "Make", "Model", "Type", "Color", "Odometer", "Price");
    }


}
