package com.panchuk.tax.menu;

import com.panchuk.tax.constant.ProjectConstant;
import com.panchuk.tax.menu.command.ExitCommand;
import com.panchuk.tax.menu.command.find_command.FindTaxCommand;
import com.panchuk.tax.menu.command.user_command.ExitToMainMenuCommand;
import com.panchuk.tax.util.PrettyConsolePrinting;

import java.util.LinkedHashMap;
import java.util.Map;

public class FindTaxMenu {
    private final Map<String, MenuItem> menuItems;

    public FindTaxMenu() {
        menuItems = new LinkedHashMap<>();
        menuItems.put("1", new FindTaxCommand(ProjectConstant.FIND_TAX_BY_ID_PAYMENT));
        menuItems.put("2", new FindTaxCommand(ProjectConstant.FIND_BY_TYPE));
        menuItems.put("3", new FindTaxCommand(ProjectConstant.FIND_BY_RANGE));
        menuItems.put("main menu", new ExitToMainMenuCommand());
        menuItems.put("exit", new ExitCommand());
    }

    public void execute(String command) {
        menuItems.getOrDefault(command, () -> System.out.println("\uD83D\uDDF2 Incorrect command! Try again!!!\n")).execute();
    }

    @Override
    public String toString() {
        return PrettyConsolePrinting.printMenu(menuItems, "Find & Sort Tax Menu") + "\n";
    }
}
