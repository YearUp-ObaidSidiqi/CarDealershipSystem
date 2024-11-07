package com.yearup.contracts;

import com.yearup.dealership.Vehicle;

public class SalesContract extends Contract{

    private final double salesTaxRate = 0.05;
    private double recordingFee;
    private boolean isFinanced;
    private double processingFee;
    private double salesTaxAmount;

    public double getSalesTaxRate() {
        return salesTaxRate;
    }

    public double getRecordingFee() {
        return recordingFee;
    }

    public void setRecordingFee(double recordingFee) {
        this.recordingFee = recordingFee;
    }

    public void setFinanced(boolean financed) {
        isFinanced = financed;
    }

    public double getProcessingFee() {
        return processingFee;
    }

    public void setProcessingFee(double processingFee) {
        this.processingFee = processingFee;
    }

    public double getSalesTaxAmount() {
        return salesTaxAmount;
    }

    public void setSalesTaxAmount(double salesTaxAmount) {
        this.salesTaxAmount = salesTaxAmount;
    }

    public SalesContract(String date, String customerName, String customerEmail, Vehicle vehicleSold, boolean isFinanced) {
        super(date, customerName, customerEmail, vehicleSold);
        this.salesTaxAmount = vehicleSold.getPrice() * salesTaxRate;
        this.recordingFee = 100;
        this.processingFee = (vehicleSold.getPrice() < 10000) ? 295 : 495;
        this.isFinanced = isFinanced;
    }

    public SalesContract(String date, String customerName, String customerEmail, Vehicle vehicleSold, double salesTaxAmount,double recordingFee, double processingFee, boolean isFinanced) {
        super(date, customerName, customerEmail, vehicleSold);
        this.recordingFee = recordingFee;
        this.salesTaxAmount = salesTaxAmount;
        this.processingFee = processingFee;
        this.isFinanced = isFinanced;
    }

    private double calculateProcessingFee() {
        double price = super.getVehicleSold().getPrice();
        return price > 10000 ? 495 : 295;
    }
    public boolean isFinanced() {
        return isFinanced;
    }

    public static Contract buildFromEncodedData(String encodedData) {
        String[] cols = encodedData.split("\\|");
        String contractDate = cols[1];
        String contractName = cols[2];
        String contractEmail = cols[3];
        int vehicleVin = Integer.parseInt(cols[4]);
        int vehicleYear = Integer.parseInt(cols[5]);
        String vehicleMake = cols[6];
        String vehicleModel = cols[7];
        String vehicleType = cols[8];
        String vehicleColor = cols[9];
        int vehicleMiles = Integer.parseInt(cols[10]);
        double vehiclePrice = Double.parseDouble(cols[11]);
        double salesContractTaxes = Double.parseDouble(cols[12]);
        double salesContractRecordingFee = Double.parseDouble(cols[13]);
        double salesContractProcessingFee = Double.parseDouble(cols[14]);
        boolean isFinanced = Boolean.parseBoolean(cols[16]);
        Vehicle v = new Vehicle(vehicleVin, vehicleYear, vehicleMake, vehicleModel, vehicleType, vehicleColor, vehicleMiles, vehiclePrice);
        return new SalesContract(contractDate,contractName, contractEmail, v,salesContractTaxes,salesContractRecordingFee, salesContractProcessingFee, isFinanced);
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

    @Override
    public String toString(){
        return "Contract for " + super.getCustomerName() + " to PURCHASE " + super.getVehicleSold();
    }

    @Override
    public String encode() {
        return "SALE|" +
                this.getDate() + "|" +
                this.getCustomerName() + "|" +
                this.getCustomerEmail() + "|" +
                this.getVehicleSold().encode() + "|" +
                this.getSalesTaxAmount() + "|" +
                this.getRecordingFee() + "|" +
                this.getProcessingFee() + "|" +
                this.getTotalPrice() + "|" +
                (this.isFinanced() ? "YES" : "NO") + "|" +
                this.getMonthlyPayment();
    }


}