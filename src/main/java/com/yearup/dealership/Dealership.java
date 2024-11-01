package com.yearup.dealership;

import java.util.ArrayList;

public class Dealership {

    ArrayList<Vehicle> vehicles = new ArrayList<Vehicle>();


    private String name;
    private String address;
    private String phone;

    public Dealership(String name, String address, String phone) {
        this.name = name;
        this.address = address;
        this.phone = phone;
    }
    public void addVehicle(int vin, int Year, String Make, String Model, String Type, String Color, int Odometer, double Price) {
        vehicles.add(new Vehicle(vin, Year, Make, Model, Type, Color, Odometer, Price));
    }
    public ArrayList<Vehicle> getAllVehicles() {
        return vehicles;
    }

    public ArrayList<Vehicle> getVehiclesByMake(String Make) {
        ArrayList<Vehicle> matching = new ArrayList<>();

        for (Vehicle vehicle : vehicles) {
            if (vehicle.getMake().equalsIgnoreCase(Make)) {
                matching.add(vehicle);
            }
        }
        return matching;
    }
    public void removeVehicle(int VIN) {
        Vehicle vehicleToRemove = null;

        for (Vehicle vehicle : vehicles) {
            if (vehicle.getVin() == VIN) {
                vehicleToRemove = vehicle;
                break;
            }
        }
        if (vehicleToRemove != null) {
            vehicles.remove(vehicleToRemove);
            //System.out.println("Vehicle with VIN " + VIN + " removed.");
        } else {
            //System.out.println("Vehicle with VIN " + VIN + " not found.");
        }
    }

    public ArrayList<Vehicle> getVehiclesByModel(String Model){
        ArrayList<Vehicle> matching = new ArrayList<>();
        for (Vehicle vehicle : vehicles){
            if (vehicle.getModel().equalsIgnoreCase(Model)){
                matching.add(vehicle);
            }
        }
        return matching;
    }
    public ArrayList<Vehicle> getVehicleByMakeModel(String Make, String Model){
        ArrayList<Vehicle> matching = new ArrayList<>();
        for (Vehicle vehicle : vehicles){
            if (vehicle.getModel().equalsIgnoreCase(Model) && vehicle.getMake().equalsIgnoreCase(Make)){
                matching.add(vehicle);
            }
        }
        return matching;
    }

    public ArrayList<Vehicle> getVehiclesByType(String Type){
        ArrayList<Vehicle> matching = new ArrayList<>();
        for (Vehicle vehicle : vehicles){
            if (vehicle.getVehicleType().equalsIgnoreCase(Type)) {
                matching.add(vehicle);
            }
        }
        return matching;
    }
    public ArrayList<Vehicle> getVehiclesByColor(String Color) {
        ArrayList<Vehicle> matching = new ArrayList<>();
        for (Vehicle vehicle : vehicles) {
            if (vehicle.getColor().equalsIgnoreCase(Color)) {
                matching.add(vehicle);
            }
        }
        return matching;
    }

    public ArrayList<Vehicle> getVehiclesByYear(int minYear, int maxYear) {
        ArrayList<Vehicle> matching = new ArrayList<>();
        for (Vehicle vehicle : vehicles) {
            if (vehicle.getYear() >= minYear && vehicle.getYear() <= maxYear) {
                matching.add(vehicle);
            }
        }
        return matching;
    }
    public ArrayList<Vehicle> getVehiclesByPrice(int minPrice, int maxPrice) {
        ArrayList<Vehicle> matching = new ArrayList<>();
        for (Vehicle vehicle : vehicles) {
            if (vehicle.getPrice() >= minPrice && vehicle.getPrice() <= maxPrice) {
                matching.add(vehicle);
            }
        }
        return matching;
    }

    public String getName() {
        return name;
    }
    public String getAddress() {
        return address;
    }
    public String getPhone() {
        return phone;
    }

    public boolean vehicleExists(int vin) {
        for (Vehicle vehicle : vehicles) {
            if (vehicle.getVin() == vin) {
                return true;
            }
        }
        return false;
    }
}

