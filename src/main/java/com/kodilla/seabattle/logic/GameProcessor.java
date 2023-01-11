package com.kodilla.seabattle.logic;

import com.kodilla.seabattle.presentation.Drawer;
import com.kodilla.seabattle.presentation.Keyboard;

public class GameProcessor {

    public void showGameMenu() {
        Menu menu = new Menu();
        Drawer drawer = new Drawer();
        Keyboard keyboard = new Keyboard();
        Validator validator = new Validator();

        drawer.titleDrawer();
        drawer.menuDrawer(menu);
        drawer.askForSelect();
        boolean incorrect = true;

        while (incorrect) {
            int key = keyboard.getInt();
            if (validator.validateForMenu(key)) {

                menu.selectOption(key);

                return;
            } else {
                drawer.incorrectSelectionMessage();
            }
        }

    }

    public void exitGame() {
        //temporarily
        System.out.println("exit game");
    }

    public void showScoreBoard() {
        //temporarily
        System.out.println("score");
    }

    public void showSettings() {
        //temporarily
        System.out.println("settings");
    }

    public void startGame() {
        //temporarily
        System.out.println("start game");
    }

}
