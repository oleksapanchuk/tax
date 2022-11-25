package com.panchuk.tax.menu.command;

import com.panchuk.tax.menu.MenuItem;
import org.apache.log4j.Logger;

public class ExitCommand implements MenuItem {
    static final Logger logger = Logger.getLogger(ExitCommand.class);

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