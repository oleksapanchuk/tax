package com.panchuk.tax.util;

import com.panchuk.tax.constant.ProjectConstant;
import com.panchuk.tax.controller.TaxController;
import com.panchuk.tax.menu.MainMenu;
import com.panchuk.tax.model.TaxType;
import com.panchuk.tax.model.User;
import com.panchuk.tax.service.pretty_print.PrettyConsolePrinting;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Reader {

    private static final Scanner scanner;
    private static final BufferedReader bf;

    static {
        scanner = new Scanner(System.in);

        bf = new BufferedReader(new InputStreamReader(System.in));
    }

    public static String inputMenuCommand(String menu) {
        System.out.print(menu);
        System.out.print("\u21AA ");
        return scanner.nextLine();
    }

    public static boolean inputIsTrue(String inputAim) {

        System.out.print("\n\u2712 " + inputAim);

        try {
            String inputData;

            while (true) {

                inputData = bf.readLine().trim().toLowerCase();

                if (inputData.equals("yes")) return true;
                else if (inputData.equals("no")) return false;
                else System.out.print("Input correct id number: ");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static User.Sex inputSex(String inputAim) {

        System.out.print("\u2712 " + inputAim);

        try {
            int inputData;

            while (true) {

                inputData = Integer.parseInt(bf.readLine().trim());

                switch (inputData) {
                    case 1 -> {
                        return User.Sex.male;
                    }
                    case 2 -> {
                        return User.Sex.female;
                    }
                    case 3 ->{
                        return User.Sex.other;
                    }
                }

                System.out.println(ProjectConstant.TEXT_WENT_WRONG);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static int inputIdUser(String inputAim, Pattern pattern) {

        System.out.print("\u2712 " + inputAim);

        try {
            int inputData;

            while (true) {

                inputData = Integer.parseInt(bf.readLine().trim());

                if (Validator.validationIdNumber(inputData, pattern)) return inputData;
                else System.out.print("Input correct id number: ");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static int inputInt(String inputAim) {

        System.out.print("\u2712 " + inputAim);

        try {
            String inputData;

            while (true) {

                inputData = bf.readLine().trim();

                if (ProjectConstant.VALID_ID_NUMBER.matcher(inputData).matches()) return Integer.parseInt(inputData);
                else System.out.print(ProjectConstant.TEXT_WENT_WRONG);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static double inputDouble(String inputAim) {

        System.out.print("\u2712 " + inputAim);

        try {
            String inputData;

            while (true) {

                inputData = bf.readLine().trim();

                if (Validator.validationDouble(inputData)) return Double.parseDouble(inputData);
                else System.out.print("Input correct id number: ");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static String inputString(String inputAim, Pattern pattern) {

        System.out.print("\u2712 " + inputAim);

        try {
            String inputData;

            while (true) {

                    inputData = bf.readLine().trim();

                if (Validator.validationString(inputData, pattern)) return inputData;
                else System.out.print("Input valid value: ");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static List<TaxType> inputTax() {

        List<TaxType> taxList = new ArrayList<>();

        int counter = 0;
        while (true) {

            if (counter > 0) {
                if (!inputIsTrue("Do you want to add one more tax (\"yes\" or \"no\"): ")) return taxList;
                else System.out.println(ProjectConstant.TEXT_INCORRECT_VALUE);
            }

            PrettyConsolePrinting.printString("CREATION TAX");

            taxList.add(TaxController.createTax(
                    inputIdPayment(),
                    inputTaxType(),
                    inputPositiveDouble(),
                    inputString("Input date of payment: ", ProjectConstant.VALID_DATE)));

            counter++;
        }

    }

    public static int inputTaxType() {
        int type;
        while (true) {
            type = inputInt("Input the type of tax: ");
            if (type <= 0 || type > 7) System.out.print("Entered type is wrong.\n");
            else break;
        }
        return type;
    }

    public static int inputIdPayment() {
        int idPayment;
        while (true) {
            idPayment = inputInt("Input id payment of tax: ");
            if (!Validator.validationIdNumberForTax(idPayment)) System.out.print("Entered id payment is wrong.\n");
            else break;
        }
        return idPayment;
    }

    public static double inputPositiveDouble() {
        double value;
        while (true) {
            value = inputDouble("Input value money to tax: ");
            if (value <= 0) System.out.print("Entered id is wrong.\n");
            else break;
        }
        return value;
    }

}
