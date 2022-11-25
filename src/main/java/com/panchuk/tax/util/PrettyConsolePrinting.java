package com.panchuk.tax.util;

import com.panchuk.tax.constant.ColorConstant;
import com.panchuk.tax.constant.ProjectConstant;
import com.panchuk.tax.menu.MenuItem;
import com.panchuk.tax.model.TaxType;
import com.panchuk.tax.model.User;
import org.nocrala.tools.texttablefmt.BorderStyle;
import org.nocrala.tools.texttablefmt.CellStyle;
import org.nocrala.tools.texttablefmt.ShownBorders;
import org.nocrala.tools.texttablefmt.Table;

import java.util.List;
import java.util.Map;

public class PrettyConsolePrinting {

    public static void printUserList(List<User> userList) {
        CellStyle centerStyle = new CellStyle(CellStyle.HorizontalAlign.CENTER);
        Table table = new Table(7, BorderStyle.UNICODE_ROUND_BOX, ShownBorders.ALL);

        if (userList == null) {
            printString(ProjectConstant.TEXT_WENT_WRONG);
            return;
        }

        table.addCell(ColorConstant.YELLOW_BOLD + "  ID  " + ColorConstant.RESET, centerStyle);
        table.addCell(ColorConstant.YELLOW_BOLD + "  First Name  " + ColorConstant.RESET, centerStyle);
        table.addCell(ColorConstant.YELLOW_BOLD + "  Last Name  " + ColorConstant.RESET, centerStyle);
        table.addCell(ColorConstant.YELLOW_BOLD + "  Sex  " + ColorConstant.RESET, centerStyle);
        table.addCell(ColorConstant.YELLOW_BOLD + "  Email  " + ColorConstant.RESET, centerStyle);
        table.addCell(ColorConstant.YELLOW_BOLD + "  Date of Birth  " + ColorConstant.RESET, centerStyle);
        table.addCell(ColorConstant.YELLOW_BOLD + "  Total Amount Tax  " + ColorConstant.RESET, centerStyle);

        for (User user : userList) {
            table.addCell(ColorConstant.CYAN_BOLD_BRIGHT + user.getId() + ColorConstant.RESET, centerStyle);
            table.addCell(ColorConstant.CYAN_BRIGHT + " " + user.getFirstName() + " " + ColorConstant.RESET);
            table.addCell(ColorConstant.CYAN_BRIGHT + " " + user.getLastName() + " " + ColorConstant.RESET);
            table.addCell(ColorConstant.CYAN_BRIGHT + " " + user.getSex().toString() + " " + ColorConstant.RESET, centerStyle);
            table.addCell(ColorConstant.CYAN_BRIGHT + " " + user.getEmail() + " " + ColorConstant.RESET);
            table.addCell(ColorConstant.CYAN_BRIGHT + " " + user.getDateOfBirth() + " " + ColorConstant.RESET, centerStyle);
            table.addCell(ColorConstant.CYAN_BRIGHT + " " +
                    String.format("%.5f", user.calculateTotalAmountOfTax()) + " " + ColorConstant.RESET, centerStyle);
        }

        System.out.println(table.render());

    }

    public static void printTaxList(List<TaxType> taxList, boolean isPrintTaxAndUser) {
        CellStyle centerStyle = new CellStyle(CellStyle.HorizontalAlign.CENTER);
        Table table = new Table(6, BorderStyle.UNICODE_ROUND_BOX, ShownBorders.ALL);

        String titleName = isPrintTaxAndUser ? "  User Name  " : "  Name of Tax  ";

        table.addCell(ColorConstant.GREEN_BOLD + "  ID payment  " + ColorConstant.RESET, centerStyle);
        table.addCell(ColorConstant.GREEN_BOLD + "  Type of Tax  " + ColorConstant.RESET, centerStyle);
        table.addCell(ColorConstant.GREEN_BOLD + titleName + ColorConstant.RESET, centerStyle);
        table.addCell(ColorConstant.GREEN_BOLD + "  Value  " + ColorConstant.RESET, centerStyle);
        table.addCell(ColorConstant.GREEN_BOLD + "  Amount of Tax  " + ColorConstant.RESET, centerStyle);
        table.addCell(ColorConstant.GREEN_BOLD + "  Date Payment  " + ColorConstant.RESET, centerStyle);

        for (TaxType tax : taxList) {
            table.addCell(ColorConstant.WHITE_BRIGHT + tax.getIdNumber() + ColorConstant.RESET, centerStyle);
            table.addCell(ColorConstant.WHITE_BRIGHT + " " + tax.getType() + " " + ColorConstant.RESET, centerStyle);
            table.addCell(ColorConstant.WHITE_BRIGHT + " " + tax.getNameTax() + " " + ColorConstant.RESET);
            table.addCell(ColorConstant.WHITE_BRIGHT + " " + String.format("%.5f", tax.getValue()) + " " + ColorConstant.RESET, centerStyle);
            table.addCell(ColorConstant.WHITE_BRIGHT + " " + String.format("%.5f", tax.getAmountOfTax()) + " " + ColorConstant.RESET, centerStyle);
            table.addCell(ColorConstant.WHITE_BRIGHT + " " + tax.getDatePayment() + " " + ColorConstant.RESET, centerStyle);
        }

        System.out.println(table.render());

    }

    public static void printUser(User user) {

        CellStyle rightStyle = new CellStyle(CellStyle.HorizontalAlign.RIGHT);
        Table table = new Table(2, BorderStyle.UNICODE_ROUND_BOX, ShownBorders.SURROUND_HEADER_AND_COLUMNS);

        table.addCell(ColorConstant.YELLOW_BOLD_BRIGHT + "    " + user.getFirstName() + " " +  user.getLastName()
                        + "    " + ColorConstant.RESET, new CellStyle(CellStyle.HorizontalAlign.CENTER), 2);
        table.addCell(ColorConstant.GREEN_BOLD + " ID: " + ColorConstant.RESET, rightStyle);
        table.addCell(ColorConstant.PURPLE + " " + user.getId() + ColorConstant.RESET);
        table.addCell(ColorConstant.GREEN_BOLD + " Sex: " + ColorConstant.RESET, rightStyle);
        table.addCell(ColorConstant.PURPLE + " " + user.getSex().toString() + ColorConstant.RESET);
        table.addCell(ColorConstant.GREEN_BOLD + " Email: " + ColorConstant.RESET, rightStyle);
        table.addCell(ColorConstant.PURPLE + " " + user.getEmail() + "  " + ColorConstant.RESET);
        table.addCell(ColorConstant.GREEN_BOLD + " Date of Birth: " + ColorConstant.RESET, rightStyle);
        table.addCell(ColorConstant.PURPLE + " " + user.getDateOfBirth() + "  " + ColorConstant.RESET);
        table.addCell(ColorConstant.GREEN_BOLD + " Total Amount of Tax: " + ColorConstant.RESET, rightStyle);
        table.addCell(ColorConstant.PURPLE + " " + user.calculateTotalAmountOfTax() + ColorConstant.RESET);

        System.out.println(table.render());

        if (user.getTax() == null || user.getTax().size() == 0) printString("THIS USER HAS NOT ANY TAX");
        else printTaxList(user.getTax(), false);

    }

    public static String printMenu(Map<String, MenuItem> menu, String nameMenu) {
        Table table = new Table(1, BorderStyle.UNICODE_ROUND_BOX, ShownBorders.NONE);

        String menuUnderline = ":" + (nameMenu.equals("Menu") ? "                   " : "               ");

        table.addCell("\n" + ColorConstant.CYAN_UNDERLINED + nameMenu  + menuUnderline
                + ColorConstant.RESET, new CellStyle(CellStyle.HorizontalAlign.LEFT));
        menu.forEach((k, v) -> table.addCell( " \t" + v + ColorConstant.RESET));
        table.addCell(ColorConstant.CYAN + "────────────────────────"
                + ColorConstant.RESET, new CellStyle(CellStyle.HorizontalAlign.LEFT));
        return table.render();
    }

    public static void printString(String text) {
        Table table = new Table(1, BorderStyle.UNICODE_ROUND_BOX, ShownBorders.ALL);
        table.addCell("  " + text + "  ");
        System.out.println("\n" + table.render());
    }

}
