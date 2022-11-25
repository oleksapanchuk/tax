package com.panchuk.tax.menu.command;

import com.panchuk.tax.Main;
import com.panchuk.tax.menu.MenuItem;
import org.apache.log4j.Logger;

public class ExitCommand implements MenuItem {
    static final Logger logger = Logger.getLogger(Main.class);

    @Override
    public void execute() {
        logger.info("Program finished!");
        System.exit(0);
    }

    @Override
    public String toString() {
        return "\u274C Exit";
    }
}