package com.yearup.fileManager;

import com.yearup.contracts.Contract;
import com.yearup.contracts.SalesContract;
import com.yearup.contracts.LeaseContract; // Assuming LeaseContract exists
import com.yearup.dealership.Vehicle;
import java.io.*;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class ContractFileManager {

    public static ArrayList<Contract> getFromCSV(String filename){
        ArrayList<Contract> results = new ArrayList<>();
        try{
            BufferedReader bufferedReader = new BufferedReader(new FileReader(filename));

            String line;

            while((line = bufferedReader.readLine()) != null){
                String[] newLine = line.split("\\|");
                if(newLine.length >= 16){
                    if(newLine[0].equalsIgnoreCase("SALE")){

                        results.add(SalesContract.buildFromEncodedData(line));
                    }
                    else if (newLine[0].equalsIgnoreCase("LEASE")){

                        results.add(LeaseContract);
                    }
                    else{
                    }
                    System.out.println("***ERROR!! Unknown contract type: " + newLine[0]);
                }
            }
            bufferedReader.close();
        }catch(Exception e){
            System.out.println("***ERROR!! Reading contracts CSV");
            System.out.println(e.getMessage());
        }
        return results;
    }

    public void writeContractsToCSV(String fileName) {
        try (var bufferedWriter = new BufferedWriter(new FileWriter(fileName))) {
            bufferedWriter.write("ContractType|Date|CustomerName|CustomerEmail|VIN|Year|Make|Model|Type|Color|Mileage|Price|LeaseTerm|MonthlyPayment|DueAtSigning|EndValue|ExpectedEndingValue\n");
            for (Contract contract : contractStorage) {
                Vehicle vehicle = contract.getVehicleSold();
                String line;
                if (contract instanceof SalesContract salesContract) {
                    line = String.format("%s|%s|%s|%s|%d|%d|%s|%s|%s|%s|%d|%.2f|||%.2f|%.2f|%s\n",
                            "SALE",
                            contract.getDate(),
                            contract.getCustomerName(),
                            contract.getCustomerEmail(),
                            vehicle.getVin(),
                            vehicle.getYear(),
                            vehicle.getMake(),
                            vehicle.getModel(),
                            vehicle.getVehicleType(),
                            vehicle.getColor(),
                            vehicle.getOdometer(),
                            vehicle.getPrice(),
                            salesContract.getTotalPrice(),       // Total price with tax and fees
                            salesContract.getMonthlyPayment(),   // Monthly payment if financed
                            salesContract.isFinanced() ? "YES" : "NO" // Financing status
                    );
                } else if (contract instanceof LeaseContract leaseContract) {
                    line = String.format("%s|%s|%s|%s|%d|%d|%s|%s|%s|%s|%d|%.2f|%d|%.2f|%.2f|%.2f|%.2f\n",
                            "LEASE",
                            contract.getDate(),
                            contract.getCustomerName(),
                            contract.getCustomerEmail(),
                            vehicle.getVin(),
                            vehicle.getYear(),
                            vehicle.getMake(),
                            vehicle.getModel(),
                            vehicle.getVehicleType(),
                            vehicle.getColor(),
                            vehicle.getOdometer(),
                            vehicle.getPrice(),
                            leaseContract.getLeasingTerm(),             // Lease term (36 months)
                            leaseContract.getMonthlyPayment(),      // Monthly payment
                            leaseContract.getExpectedEndingValue(), // Expected ending value
                            "",                                     // Placeholder for due at signing (if applicable)
                            ""                                      // Placeholder for any additional fields (if needed)
                    );
                } else {
                    System.out.println("***ERROR!! Unsupported contract type");
                    continue;
                }

                bufferedWriter.write(line);
            }
        } catch (IOException e) {
            System.out.println("***ERROR!! Writing to contracts CSV");
            System.out.println(e.getMessage());
        }
    }


    public ArrayList<Contract> getContractStorage() {
        return contractStorage;
    }



}
