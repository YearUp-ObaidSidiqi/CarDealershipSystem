package com.yearup.contracts;

import com.yearup.dealership.Vehicle;

public class SalesContract extends Contract{

    private final double salesTaxRate = 0.05;
    private final int recordingFee = 100;
    private boolean isFinanced;
    private double calculateProcessingFee() {
        double price = super.getVehicleSold().getPrice();
        return price > 10000 ? 495 : 295;
    }

    public SalesContract(String date, String customerName, String customerEmail, Vehicle vehicleSold, boolean isFinanced) {
        super(date, customerName, customerEmail, vehicleSold);
        this.isFinanced = isFinanced;
    }
    public SalesContract() {
    }
    public double getSalesTax() {
        return salesTaxRate;
    }
    public int getRecordingFee() {
        return recordingFee;
    }

    @Override /// I still don't know, copied fromm [Ali] extended [Yming]
    public double getMonthlyPayment() {
        if (!isFinanced) {
            return 0.0;
        }
        double amount = getTotalPrice();
        int months = getVehicleSold().getPrice() >= 10000 ? 48 : 24;
        double rate = getVehicleSold().getPrice() >= 10000 ? 0.0425 : 0.0525;
        return amount * (rate/12 * Math.pow(1 + rate/12, months)) / (Math.pow(1 + rate/12, months) - 1);
    }
    @Override
    public double getTotalPrice(){
        Vehicle vehicle = super.getVehicleSold();
        double vehiclePrice = vehicle.getPrice();
        double salesTax = vehiclePrice * salesTaxRate;
        return vehiclePrice + salesTax + recordingFee + calculateProcessingFee();
    }
}
