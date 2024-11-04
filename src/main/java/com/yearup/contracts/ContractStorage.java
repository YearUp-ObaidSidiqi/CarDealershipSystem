package com.yearup.contracts;

import com.yearup.dealership.Vehicle;
import java.util.ArrayList;


public class ContractStorage {

    public ContractStorage(){}

    ArrayList<Contract> contract = new ArrayList<>();
    ArrayList<Vehicle> vehicles = new ArrayList<>();
    String contractType;
    double deposit;
    double tax;
    double fees;
    double totalCost;
    boolean warranty;
    double leaseMonthlyPayment;
    boolean isFinanced;

    public ContractStorage(ArrayList<Contract> contract, String contractType, double deposit,
                           double tax, double fees, double totalCost, boolean warranty, double leaseMonthlyPayment) {
        this.contract = contract;
        this.contractType = contractType;
        this.deposit = deposit;
        this.tax = tax;
        this.fees = fees;
        this.totalCost = totalCost;
        this.warranty = warranty;
        this.leaseMonthlyPayment = leaseMonthlyPayment;
    }
    public void addContract(String date, String customerName,String customerEmail, Vehicle vehicleSold ){

        Contract newContract;
        if("sales".equalsIgnoreCase(contractType)){
            newContract = new SalesContract(date, customerName, customerEmail, vehicleSold,isFinanced);
        }else {
            newContract = new LeaseContract(date, customerName, customerEmail, vehicleSold);
        }
        contract.add(newContract);
    }
}
