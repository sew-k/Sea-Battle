package com.kodilla.seabattle.presentation;

import com.kodilla.seabattle.logic.Menu;

public class Drawer {

    public void menuDrawer(Menu menu) {
        for (String menuPosition : menu.getOptions()) {
            System.out.println("[" + menu.getOptions().indexOf(menuPosition) + "] - " + menuPosition);
        }
    }

    public void titleDrawer() {
        System.out.println("");
        System.out.println("SEA BATTLE");
        System.out.println("");
    }

    public void askForSelect() {
        System.out.println("Please select an option: ");
    }

    public void incorrectSelectionMessage() {
        System.out.print("Incorrect selection. Please, select again: ");
    }
}
