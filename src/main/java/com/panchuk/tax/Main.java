package com.panchuk.tax;

import com.panchuk.tax.menu.MainMenu;

import static com.panchuk.tax.util.Reader.inputMenuCommand;

public class Main {

    private static final MainMenu mainMenu = new MainMenu();
    public static void main(String[] args) {
        while (true) {
            mainMenu.execute(inputMenuCommand(mainMenu.toString()));
        }
    }
}
