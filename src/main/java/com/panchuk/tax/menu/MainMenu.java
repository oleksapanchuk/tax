package com.panchuk.tax.menu;

import com.panchuk.tax.menu.command.*;
import com.panchuk.tax.menu.command.find_command.FindTaxCommands;
import com.panchuk.tax.menu.command.find_command.FindUserCommands;
import com.panchuk.tax.util.PrettyConsolePrinting;
import org.apache.log4j.Logger;

import java.util.LinkedHashMap;
import java.util.Map;

public class MainMenu {
    static final Logger logger = Logger.getLogger(MainMenu.class);

    private final Map<String, MenuItem> menuItems;

    public MainMenu() {
        menuItems = new LinkedHashMap<>();
        menuItems.put("show users", new GetAllUsersCommand());
        menuItems.put("get user", new GetUserCommand());
        menuItems.put("add user", new AddUserCommand());
        menuItems.put("find users", new FindUserCommands());
        menuItems.put("find taxes", new FindTaxCommands());
        menuItems.put("exit", new ExitCommand());
    }

    public void execute(String command) {
        logger.info("Execute \"Main Menu\"");
        menuItems.getOrDefault(command, () -> System.out.println("\uD83D\uDDF2 Incorrect command! Try again!!!\n")).execute();
    }

    @Override
    public String toString() {
        return PrettyConsolePrinting.printMenu(menuItems, "Menu") + "\n";
    }
}
