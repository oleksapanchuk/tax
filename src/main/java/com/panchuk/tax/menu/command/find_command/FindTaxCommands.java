package com.panchuk.tax.menu.command.find_command;

import com.panchuk.tax.menu.FindTaxMenu;
import com.panchuk.tax.menu.MenuItem;

import static com.panchuk.tax.util.Reader.inputMenuCommand;

public class FindTaxCommands implements MenuItem {

    @Override
    public void execute() {
        FindTaxMenu findTaxMenu = new FindTaxMenu();
        while (true) {
            findTaxMenu.execute(inputMenuCommand(findTaxMenu.toString()));
        }
    }

    @Override
    public String toString() {
        return "\uD83D\uDD0E Find & Sort Taxes";
    }
}
