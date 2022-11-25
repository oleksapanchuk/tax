package com.panchuk.tax.menu.command.find_command;

import com.panchuk.tax.menu.FindTaxMenu;
import com.panchuk.tax.menu.MenuItem;
import com.panchuk.tax.menu.command.AddUserCommand;
import org.apache.log4j.Logger;

import static com.panchuk.tax.util.Reader.inputMenuCommand;

public class FindTaxCommands implements MenuItem {
    static final Logger logger = Logger.getLogger(FindTaxCommands.class);

    @Override
    public void execute() {
        logger.info("Execute \"FindTaxCommands\"");

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
