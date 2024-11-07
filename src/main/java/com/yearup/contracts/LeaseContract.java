package com.yearup.contracts;

import com.yearup.dealership.Vehicle;

import java.util.regex.Pattern;

public class LeaseContract extends Contract {
    private double expectedEndingValue = 0.5;
    private double leaseFee = 0.07;
    private double monthlyPayment;

    private final int leasingTerm = 36;
    private final double financeRate= 0.04;

    public LeaseContract(String date, String customerName, String customerEmail, Vehicle vehicleSold) {
        super(date, customerName, customerEmail, vehicleSold);
        this.expectedEndingValue = vehicleSold.getPrice() * expectedEndingValue; // 50% of its price
        this.leaseFee = vehicleSold.getPrice() * leaseFee; // 7% of its price
    }
    public double getExpectedEndingValue() {
        return expectedEndingValue;
    }
    public void setExpectedEndingValue(double expectedEndingValue) {
        this.expectedEndingValue = expectedEndingValue;
    }
    public double getLeaseFee() {
        return leaseFee;
    }
    public void setLeaseFee(double leaseFee) {
        this.leaseFee = leaseFee;
    }

    @Override
    public double getMonthlyPayment() {
        double totalPrice = getTotalPrice();
        double monthlyRate = financeRate / 12;
        return totalPrice *
                (monthlyRate * Math.pow(1 + monthlyRate, leasingTerm)) /
                (Math.pow(1 + monthlyRate, leasingTerm) - 1);
    }
    public int getLeasingTerm() {
        return leasingTerm;
    }

    public double getFinanceRate() {
        return financeRate;
    }
    @Override
    public double getTotalPrice(){

        return getMonthlyPayment() * leasingTerm;
    }
    @Override
    public String toString(){
        return "Contract for " + super.getCustomerName() + " to LEASE " + super.getVehicleSold();
    }

    @Override
    public String encode() {
        return "LEASE|" +
                this.getDate() + "|" +
                this.getCustomerName() + "|" +
                this.getCustomerEmail() + "|" +
                this.getVehicleSold().encode() + "|" +
                this.getExpectedEndingValue() + "|" +
                this.getLeaseFee() + "|" +
                this.getTotalPrice() + "|" +
                this.getMonthlyPayment();
    }

    public static Contract buildFromEncodedData (String encodedData){
        String [] line = encodedData.split(Pattern.quote("|"));
        String contractDate = line[1];
        String contractName = line[2];
        String contractEmail = line[3];
        int contractVehicleVIN = Integer.parseInt(line [4]);
        int contractVehicleYear = Integer.parseInt(line[5]);
        String contractVehicleMake = line[6];
        String contractVehicleModel = line [7];
        String contractVehicleType = line [8];
        String contractVehicleColor = line [9];
        int contractVehicleOdometer = Integer.parseInt(line[10]);
        double contractVehiclePrice = Integer.parseInt(line[11]);
        double leaseContractExpectedEndingValue = Double.parseDouble(line[12]);
        double leaseContractLeaseFee = Double.parseDouble(line[13]);


    }

}
