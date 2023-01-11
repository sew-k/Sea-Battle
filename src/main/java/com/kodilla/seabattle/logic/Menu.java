package com.kodilla.seabattle.logic;

import com.kodilla.seabattle.presentation.Drawer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Menu {

    private final List<String> options = new ArrayList<>(Arrays.asList("Start Game","Settings","Score Board","Exit"));


    public List<String> getOptions() {
        return options;
    }

    public boolean selectOption(int selection) {
        Validator validator = new Validator();
        Drawer drawer = new Drawer();
        GameProcessor processor = new GameProcessor();
        if (validator.validateForMenu(selection)) {
            if (selection == 0) {
                processor.startGame();
            } else if (selection == 1) {
                processor.showSettings();
            } else if (selection == 2) {
                processor.showScoreBoard();
            } else if (selection == 3) {
                processor.exitGame();
            }

            return true;
        } else {
            drawer.incorrectSelectionMessage();
            return false;
        }
    }
}
