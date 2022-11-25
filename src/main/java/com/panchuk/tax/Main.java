package com.panchuk.tax;

import com.panchuk.tax.menu.MainMenu;
import org.apache.log4j.Logger;

import static com.panchuk.tax.util.Reader.inputMenuCommand;

public class Main {
    static final Logger logger = Logger.getLogger(Main.class);

    private static final MainMenu mainMenu = new MainMenu();
    public static void main(String[] args) {
        logger.info("Program started!");

        while (true) {
            mainMenu.execute(inputMenuCommand(mainMenu.toString()));
        }
    }
}
