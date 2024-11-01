package com.yearup.contracts;

import com.yearup.dealership.Vehicle;

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
    public LeaseContract() {

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
    @Override
    public double getTotalPrice(){

        return getMonthlyPayment() * leasingTerm;
    }

}
