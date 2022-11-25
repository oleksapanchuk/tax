package com.panchuk.tax.menu;

import com.panchuk.tax.menu.command.*;
import com.panchuk.tax.menu.command.user_command.*;
import com.panchuk.tax.model.User;
import com.panchuk.tax.util.PrettyConsolePrinting;
import org.apache.log4j.Logger;

import java.util.LinkedHashMap;
import java.util.Map;

public class UserMenu {
    static final Logger logger = Logger.getLogger(UserMenu.class);
    private final Map<String, MenuItem> userItems;

    public UserMenu(User user) {
        userItems = new LinkedHashMap<>();
        userItems.put("delete user", new DeleteUserCommand(user));
        userItems.put("update user", new UpdateUserCommand(user));
        userItems.put("add tax", new AddTaxCommand(user));
        userItems.put("delete tax", new DeleteTaxCommand(user));
        userItems.put("main menu", new ExitToMainMenuCommand());
        userItems.put("exit", new ExitCommand());
    }

    public void execute(String command) {
        logger.info("Execute \"User Menu\"");
        userItems.getOrDefault(command, () -> System.out.println("\uD83D\uDDF2 Incorrect command! Try again!!!\n")).execute();
    }

    @Override
    public String toString() {
        return PrettyConsolePrinting.printMenu(userItems, "User Menu") + "\n";
    }
}
