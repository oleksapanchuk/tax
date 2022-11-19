package com.panchuk.tax.menu.command.find_command;

import com.panchuk.tax.menu.FindUserMenu;
import com.panchuk.tax.menu.MenuItem;

import static com.panchuk.tax.util.Reader.inputMenuCommand;

public class FindUserCommands implements MenuItem {

    @Override
    public void execute() {

        FindUserMenu findUserMenu = new FindUserMenu();
        while (true) {
            findUserMenu.execute(inputMenuCommand(findUserMenu.toString()));
        }

    }

    @Override
    public String toString() {
        return "\uD83D\uDD0E Find & Sort Users";
    }
}
