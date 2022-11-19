package com.panchuk.tax.menu.command.user_command;

import com.panchuk.tax.Main;
import com.panchuk.tax.constant.ProjectConstant;
import com.panchuk.tax.menu.MenuItem;

public class ExitToMainMenuCommand implements MenuItem {

    @Override
    public void execute() {
        Main.main(new String[]{});
    }

    @Override
    public String toString() {
        return ProjectConstant.EMO_BACK_MAIN_MENU + " Main Menu";
    }
}
