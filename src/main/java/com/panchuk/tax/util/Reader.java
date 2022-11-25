package com.panchuk.tax.util;

import com.panchuk.tax.constant.ProjectConstant;
import com.panchuk.tax.controller.TaxController;
import com.panchuk.tax.model.TaxType;
import com.panchuk.tax.model.User;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.regex.Pattern;

public class Reader {
    static final Logger logger = Logger.getLogger(Reader.class);
    private static final Scanner scanner;
    private static final BufferedReader bf;

    private static final Map<Integer, User.Sex> gender;

    static {
        scanner = new Scanner(System.in);

        bf = new BufferedReader(new InputStreamReader(System.in));

        gender = new HashMap<>();
        gender.put(1, User.Sex.male);
        gender.put(2, User.Sex.female);
        gender.put(3, User.Sex.other);
    }

    public static String inputMenuCommand(String menu) {
        logger.info("inputMenuCommand started");
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
            LoggerController.inputException(e);
        }
        return false;
    }

    public static User.Sex inputSex(String inputAim) {

        System.out.println("Gender Type: ");
        System.out.println("\t-- 1. male\n\t-- 2. female\n\t-- 3. other");

        System.out.print("\u2712 " + inputAim);

        try {
            int inputData;

            while (true) {

                try {
                    inputData = Integer.parseInt(bf.readLine().trim());
                } catch (NumberFormatException e) {
                    System.out.println("\u26D4 Entered data is not number. Try again!");
                    System.out.print("\u2712 " + inputAim);
                    continue;
                }

                if (gender.containsKey(inputData)) return gender.get(inputData);

                System.out.println(ProjectConstant.TEXT_WENT_WRONG);

            }

        } catch (IOException e) {
            LoggerController.inputException(e);
        }
        return null;
    }

    public static int inputIdUser(String inputAim, Pattern pattern) {

        while (true) {

            int inputData = inputInt(inputAim);

            if (Validator.validationIdNumber(inputData, pattern)) return inputData;
            else System.out.println("\u26D4 Entered value is incorrect!");
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
            LoggerController.inputException(e);
        }
        return 0;
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
            LoggerController.inputException(e);
        }
        return 0;
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
            LoggerController.inputException(e);
        }
        return null;
    }

    public static List<TaxType> inputTax() {

        List<TaxType> taxList = new ArrayList<>();

        int counter = 0;
        while (true) {

            if (counter++ > 0) {
                if (!inputIsTrue("Do you want to add one more tax (\"yes\" or \"no\"): ")) return taxList;
            }

            PrettyConsolePrinting.printString("CREATION TAX");

            taxList.add(TaxController.createTax(
                    inputIdPayment(),
                    inputTaxType(),
                    inputPositiveDouble(),
                    inputString("Input date of payment (YYYY-MM-DD): ", ProjectConstant.VALID_DATE)));

        }

    }

    public static int inputTaxType() {
        int type;
        while (true) {
            type = inputInt("Input the type of tax (print \"10\" for info): ");
            if (type == 10) {
                System.out.println("""
                        Type of Tax:
                        \t\t1. Податок на дохід з основного та додаткового місць роботи
                        \t\t2. Податок на подарунки
                        \t\t3. Податок на авторські винагороди
                        \t\t4. Податок на продаж майна
                        \t\t5. Податок на перекази з-за кордону
                        \t\t6. Податок пільг на дітей
                        \t\t7. Податок на матеріальну допомогу""");
                type = inputInt("Input the type of tax: ");
            }
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
