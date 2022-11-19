package com.panchuk.tax.menu.command;

import com.panchuk.tax.menu.MenuItem;

public class ExitCommand implements MenuItem {
    @Override
    public void execute() {
        System.exit(0);
    }

    @Override
    public String toString() {
        return "\u274C Exit";
    }
}