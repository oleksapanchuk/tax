package com.panchuk.tax.menu.command.user_command;

import com.panchuk.tax.Main;
import com.panchuk.tax.constant.ProjectConstant;
import com.panchuk.tax.menu.MenuItem;
import org.apache.log4j.Logger;

public class ExitToMainMenuCommand implements MenuItem {

    static final Logger logger = Logger.getLogger(ExitToMainMenuCommand.class);

    @Override
    public void execute() {
        logger.info("Execute \"Exit To Main Menu\" Command");

        Main.main(new String[]{});
    }

    @Override
    public String toString() { return ProjectConstant.EMO_BACK_MAIN_MENU + " Main Menu"; }
}
