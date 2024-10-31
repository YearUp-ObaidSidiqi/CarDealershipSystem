package com.yearup;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Utilities {

    static Scanner scanner = new Scanner(System.in);

    public static double PromptForDouble(String prompt){
        System.out.print(prompt);
        String userInputs = scanner.nextLine();
        double userinput = Double.parseDouble(userInputs);
        return userinput;
    }
    public static String PromptForString(String prompt){
        System.out.print(prompt);
        String input = scanner.nextLine().toUpperCase();
        return input;
    }
    public static LocalDate PromptForLocalDate(String prompt){
        System.out.print(prompt);
        String input = scanner.nextLine();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(input,formatter);


        return localDate;
    }
    public static LocalTime PromptForLocalTime(String prompt){
        System.out.print(prompt);
        String input = scanner.nextLine();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm:ss, a");
        LocalTime localTime = LocalTime.parse(input,formatter);


        return localTime;
    }

    public static int PromptForInt(String prompt){
        System.out.print(prompt);
        String userInputs = scanner.nextLine();
        int userinput =Integer.parseInt(userInputs);
       // scanner.nextLine();
        return userinput;
    }

    public static boolean PromptForYesNo(String prompt){
        System.out.print(prompt + " ( Y for Yes, N for No ) ?");
        String userinput = scanner.nextLine();

        return
                (
                        userinput.equalsIgnoreCase("Y")
                                ||
                                userinput.equalsIgnoreCase("YES")
                );

    }
    public static void printStarsForSpacing() {
        System.out.println("\n");
        for(int i = 0; i < 2; i++)
            System.out.println("***************************************");
        for(int j = 0; j < 4; j++)
            System.out.println("**********   Y E A R   U P   **********");
        for (int k = 0; k < 2; k++)
            System.out.println("***************************************");
        for(int l = 0; l < 1; l++)
            System.out.println("*********   ****************   ********");
        for(int m = 0; m < 1; m++)
            System.out.println("************   **********   ***********");
        for (int n = 0; n < 1; n++)
            System.out.println("******************    *****************");
        for(int o = 0; o < 2; o++)
            System.out.println("***************************************");
        System.out.println("\n");
    }
}