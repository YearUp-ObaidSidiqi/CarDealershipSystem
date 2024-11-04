package com.yearup.fileManager;

import com.yearup.contracts.Contract;
import com.yearup.contracts.SalesContract;
import com.yearup.contracts.LeaseContract; // Assuming LeaseContract exists
import com.yearup.dealership.Vehicle;

import java.io.*;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class ContractFileManager {

    private final ArrayList<Contract> contractStorage;

    public ContractFileManager() {
        this.contractStorage = new ArrayList<>();
    }

    public void loadContracts(String fileName) throws IOException {
        try (var bufferedReader = new BufferedReader(new FileReader(fileName))) {
            String input = bufferedReader.readLine(); // Skip header if necessary

            while ((input = bufferedReader.readLine()) != null) {
                String[] tokens = input.split(Pattern.quote("|"));
                if (tokens.length < 17) {
                    System.out.println("***ERROR!! Incomplete contract data: " + input);
                    continue; // Skip if data is incomplete
                }

                Vehicle vehicle = new Vehicle(
                        Integer.parseInt(tokens[4]),    // VehicleID VIN
                        Integer.parseInt(tokens[5]),    // Year
                        tokens[6],                      // Make
                        tokens[7],                      // Model
                        tokens[8],                      // Type
                        tokens[9],                      // Color
                        Integer.parseInt(tokens[10]),   // Mileage
                        Double.parseDouble(tokens[11])  // Price
                );

                String contractType = tokens[0];
                String date = tokens[1];
                String customerName = tokens[2];
                String customerEmail = tokens[3];

                switch (contractType) {
                    case "SALE" -> contractStorage.add(
                            new SalesContract(date, customerName, customerEmail, vehicle, tokens[16].equals("YES"))
                    );
                    case "LEASE" -> contractStorage.add(
                            new LeaseContract(date, customerName, customerEmail, vehicle));
                    default -> System.out.println("***ERROR!! Unknown contract type: " + contractType);
                }
            }
        } catch (IOException e) {
            System.out.println("***ERROR!! Reading contracts CSV");
            System.out.println(e.getMessage());
        }
    }

    public void writeContractsToCSV(String fileName) {
        try (var bufferedWriter = new BufferedWriter(new FileWriter(fileName))) {
            // Optional: Write header if required
            bufferedWriter.write("ContractType|Date|CustomerName|CustomerEmail|VIN|Year|Make|Model|Type|Color|Mileage|Price|Term|MonthlyPayment|DueAtSigning|EndValue|Certified\n");

            for (Contract contract : contractStorage) {
                Vehicle vehicle = contract.getVehicleSold();
                String line;

                if (contract instanceof SalesContract salesContract) {
                    line = String.format("%s|%s|%s|%s|%d|%d|%s|%s|%s|%s|%d|%.2f|||%s|%s\n",
                            "SALE", contract.getDate(), contract.getCustomerName(), contract.getCustomerEmail(),
                            vehicle.getVin(), vehicle.getYear(), vehicle.getMake(), vehicle.getModel(),
                            vehicle.getVehicleType(), vehicle.getColor(), vehicle.getOdometer(), vehicle.getPrice(),
                            "", "", "", salesContract.isFinanced() ? "YES" : "NO"
                    );
                } else if (contract instanceof LeaseContract leaseContract) {
                    line = String.format("%s|%s|%s|%s|%d|%d|%s|%s|%s|%s|%d|%.2f|%d|%.2f|%.2f|%.2f|\n",
                            "LEASE", contract.getDate(), contract.getCustomerName(), contract.getCustomerEmail(),
                            vehicle.getVin(), vehicle.getYear(), vehicle.getMake(), vehicle.getModel(),
                            vehicle.getVehicleType(), vehicle.getColor(), vehicle.getOdometer(), vehicle.getPrice(),
                            leaseContract.getLeaseTerm(), leaseContract.getMonthlyPayment(),
                            leaseContract.getDueAtSigning(), leaseContract.getEndValue()
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
