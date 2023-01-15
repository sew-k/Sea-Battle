package com.kodilla.seabattle.logic;

import com.kodilla.seabattle.data.ScoreBoard;
import com.kodilla.seabattle.presentation.Keyboard;
import com.kodilla.seabattle.presentation.Printer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Menu extends Options {
    private final String choicesTitle = "Game Menu";
    private final List<String> options = new ArrayList<>(Arrays.asList("Start Game","Settings","Score Board","Exit"));


    public List<String> getOptions() {
        return options;
    }
    @Override
    public String getChoicesTitle() {
        return choicesTitle;
    }
    @Override
    public void selectOption() {

        super.selectOption();

        Settings settings = new Settings();
        Keyboard keyboard = new Keyboard();
        Validator validator = new Validator();
        Printer printer = new Printer();
        GameProcessor processor = new GameProcessor();
        ScoreBoard scoreBoard = new ScoreBoard();

        boolean incorrect = true;

        while (incorrect) {
            int key = keyboard.getInt();
            if (validator.validateForChoices(key, this)) {
                if (key == 0) {
                    processor.startGame();
                } else if (key == 1) {
                    printer.titleOfChoicesPrinter(settings);
                    printer.optionsPrinter(settings);
                    settings.selectOption();
                } else if (key == 2) {
                    printer.showScoreBoard();
                    processor.processGame();
                } else if (key == 3) {
                    processor.exitGame();
                }
                return;
            } else {
                printer.incorrectSelectionMessage();
            }
        }
    }


//    public void showGameMenu() {
//        Settings settings = new Settings();
//        Printer printer = new Printer();
//        Keyboard keyboard = new Keyboard();
//        Validator validator = new Validator();
//
//
//        printer.askForSelect();
//        boolean incorrect = true;
//
//        while (incorrect) {
//            int key = keyboard.getInt();
//            if (validator.validateForMenu(key)) {
//
//                menu.selectOption(key);
//
//                //return;
//            } else {
//                printer.incorrectSelectionMessage();
//            }
//        }
//    }
}
