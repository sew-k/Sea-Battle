package com.kodilla.seabattle.logic;

import com.kodilla.seabattle.data.Board;
import com.kodilla.seabattle.presentation.Keyboard;
import com.kodilla.seabattle.presentation.Printer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class PlayerSettings extends Options {
    private final String optionsTitle = "Player Settings";
    private final List<String> playerSettings = new ArrayList<>(Arrays.asList("Player vs Computer (normal)",
            "Player vs Computer (hard)",
            "Player vs Player",
            "Computer vs Computer"));
    private static int currentPlayerSettings = 0;

    @Override
    public String getOptionsTitle() {
        return optionsTitle;
    }
    @Override
    public List<String> getOptions() {
        return playerSettings;
    }

    public static int getCurrentPlayerSettings() {
        return currentPlayerSettings;
    }

    public static void setCurrentPlayerSettings(int currentPlayerSettings) {
        PlayerSettings.currentPlayerSettings = currentPlayerSettings;
    }

    @Override
    public void selectOption() {

        super.selectOption();
        Keyboard keyboard = new Keyboard();
        Validator validator = new Validator();
        Printer printer = new Printer();
        printer.askForSelect();
        boolean end = false;
        while (!end) {
            String key = keyboard.getString();
            if (validator.validateForOptions(key, this)) {
                setCurrentPlayerSettings(Integer.parseInt(key));
                printer.currentPlayerSettingsPrinter();
                end = true;
            } else {
                printer.incorrectSelectionMessage();
            }
        }
    }

}
