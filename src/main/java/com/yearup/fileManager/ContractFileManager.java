package com.yearup.fileManager;
import com.yearup.dealership.Dealership;
import com.yearup.contracts.Contract;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Pattern;

public class ContractFileManager {

    public static Dealership ReadContractFromCSV(String fileName) throws IOException {
        Contract contract;
        Dealership dealership1;

        var bufferedReader = new BufferedReader(new FileReader(fileName));
        String input = bufferedReader.readLine();
        String[] token1 = input.split(Pattern.quote("|"));

        contract = new Contract();

        while ((input = bufferedReader.readLine()) != null) {
            String[] tokens = input.split(Pattern.quote("|"));
            dealership1.addVehicle(
                    Integer.parseInt(tokens[0]),  //int VIN
                    Integer.parseInt(tokens[1]), // int year
                    tokens[2],                  // make
                    tokens[3],
                    tokens[4],
                    tokens[5],
                    Integer.parseInt(tokens[6]),
                    Double.parseDouble(tokens[7]));
        }
        bufferedReader.close();
        return dealership;
    }

}
