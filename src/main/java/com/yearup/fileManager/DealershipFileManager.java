package com.yearup.fileManager;
import com.yearup.dealership.Dealership;
import com.yearup.dealership.Vehicle;

import java.io.*;
import java.util.regex.Pattern;


public class DealershipFileManager{
    static Dealership dealership;
    public static Dealership ReadVehiclesFromCSV(String fileName) throws IOException {
            var bufferedReader = new BufferedReader(new FileReader(fileName));
            String input = bufferedReader.readLine();
            String[] token1 = input.split(Pattern.quote("|"));
            dealership = new Dealership(token1[0], token1[1], token1[2]);

            while ((input = bufferedReader.readLine()) != null) {
                String[] tokens = input.split(Pattern.quote("|"));
                dealership.addVehicle(
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
    public static void writeVehiclesToCSV(Dealership dealership) {
        try (
                var bufferedWriter = new BufferedWriter(new FileWriter("vehicles.csv"))) {
            // Write header (if needed)
            bufferedWriter.write(dealership.getName()+"|"+dealership.getAddress()+"|"+dealership.getPhone()+"\n");

            for (Vehicle vehicle : dealership.vehicles) {
                String line = String.format("%s|%s|%s|%s|%s|%s|%s|%.2f\n",
                        vehicle.getVin(),
                        vehicle.getYear(),
                        vehicle.getMake(),
                        vehicle.getModel(),
                        vehicle.getVehicleType(),
                        vehicle.getColor(),
                        vehicle.getOdometer(),
                        vehicle.getPrice());
                bufferedWriter.write(line);
            }
        } catch (IOException e) {
            System.out.println("***ERROR!! Writing to CSV");
            System.out.println(e.getMessage());
        }
    }
}
