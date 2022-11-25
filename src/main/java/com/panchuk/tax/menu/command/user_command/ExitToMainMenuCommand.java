package com.panchuk.tax.menu.command.user_command;

import com.panchuk.tax.Main;
import com.panchuk.tax.constant.ProjectConstant;
import com.panchuk.tax.menu.MenuItem;
import org.apache.log4j.Logger;

public class ExitToMainMenuCommand implements MenuItem {

    static final Logger logger = Logger.getLogger(Main.class);

    @Override
    public void execute() {
        Main.main(new String[]{});
    }

    @Override
    public String toString() {
        logger.info("back to main menu command");
        return ProjectConstant.EMO_BACK_MAIN_MENU + " Main Menu";
    }
}
