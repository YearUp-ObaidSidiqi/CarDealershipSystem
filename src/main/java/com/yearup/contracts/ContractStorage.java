package com.yearup.contracts;
import java.util.ArrayList;

public class ContractStorage {

    ArrayList<Contract> contract = new ArrayList<Contract>();
    ArrayList<Contract> con = new ArrayList<>();

    String contractType;
    double deposit;
    double tax;
    double fees;
    double totalCost;
    boolean warranty;
    double leaseMonthlyPayment;

    public ContractStorage(ArrayList<Contract> contract, String contractType, double deposit, double tax, double fees, double totalCost, boolean warranty, double leaseMonthlyPayment) {
        this.contract = contract;
        this.contractType = contractType;
        this.deposit = deposit;
        this.tax = tax;
        this.fees = fees;
        this.totalCost = totalCost;
        this.warranty = warranty;
        this.leaseMonthlyPayment = leaseMonthlyPayment;
    }
    public void addContract(String dat){
        contract.add();
    }
}
