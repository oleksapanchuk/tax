package com.panchuk.tax.menu;

import com.panchuk.tax.constant.ProjectConstant;
import com.panchuk.tax.menu.command.*;
import com.panchuk.tax.menu.command.find_command.FindUserCommand;
import com.panchuk.tax.menu.command.user_command.ExitToMainMenuCommand;
import com.panchuk.tax.util.PrettyConsolePrinting;
import org.apache.log4j.Logger;

import java.util.LinkedHashMap;
import java.util.Map;

public class FindUserMenu {
    static final Logger logger = Logger.getLogger(FindUserMenu.class);

    private final Map<String, MenuItem> menuItems;

    public FindUserMenu() {
        menuItems = new LinkedHashMap<>();
        menuItems.put("1", new FindUserCommand(ProjectConstant.FIND_BY_FIRST_NAME));
        menuItems.put("2", new FindUserCommand(ProjectConstant.FIND_BY_LAST_NAME));
        menuItems.put("3", new FindUserCommand(ProjectConstant.FIND_BY_EMAIL));
        menuItems.put("4", new FindUserCommand(ProjectConstant.SORT_BY_FIRST_NAME));
        menuItems.put("5", new FindUserCommand(ProjectConstant.SORT_BY_LAST_NAME));
        menuItems.put("6", new FindUserCommand(ProjectConstant.SORT_BY_AMOUNT));
        menuItems.put("main menu", new ExitToMainMenuCommand());
        menuItems.put("exit", new ExitCommand());
    }

    public void execute(String command) {
        logger.info("Execute \"Find User Menu\"");
        menuItems.getOrDefault(command, () -> System.out.println("\uD83D\uDDF2 Incorrect command! Try again!!!\n")).execute();
    }

    @Override
    public String toString() {
        return PrettyConsolePrinting.printMenu(menuItems, "Find & Sort User Menu") + "\n";
    }
}

