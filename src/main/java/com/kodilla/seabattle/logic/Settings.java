package com.kodilla.seabattle.logic;

import com.kodilla.seabattle.data.ScoreBoard;
import com.kodilla.seabattle.presentation.Keyboard;
import com.kodilla.seabattle.presentation.Printer;

import java.util.*;

public class Settings extends Options {
    private final String optionsTitle = "Game Settings";
    private final List<String> settings = new ArrayList<>(Arrays.asList("Ships configuration", "Players", "Game board size", "Exit settings"));
    private static Map<Integer,Integer> shipCountSettings = new HashMap<>();
    private static boolean onePlayerGame = true;


    public void setShipCountSettings() {
        //temporarily - default values
        shipCountSettings.put(4,1);
        shipCountSettings.put(3,2);
        shipCountSettings.put(2,3);
        shipCountSettings.put(1,4);
    }

    @Override
    public List<String> getOptions() {
        return settings;
    }
    @Override
    public String getOptionsTitle() {
        return optionsTitle;
    }

    public static Map<Integer, Integer> getShipCountSettings() {
        return shipCountSettings;
    }

    public static void setShipCountSettings(Map<Integer, Integer> shipCountSettings) {
        Settings.shipCountSettings = shipCountSettings;
    }

    public static boolean isOnePlayerGame() {
        return onePlayerGame;
    }

    public static void setOnePlayerGame(boolean onePlayerGame) {
        Settings.onePlayerGame = onePlayerGame;
    }

    @Override
    public void selectOption() {

        super.selectOption();
        Keyboard keyboard = new Keyboard();
        Validator validator = new Validator();
        Printer printer = new Printer();
        GameProcessor processor = new GameProcessor();

        //temporarily
        setShipCountSettings();
        boolean end = false;
        while (!end) {
            String key = keyboard.getString();
            if (validator.validateForOptions(key, this)) {
                if (Integer.parseInt(key) == 0) {
                    Map<Integer,Integer> shipCountSettings = getShipCountSettings();
                    printer.printShipCountSettings(shipCountSettings);
                    printer.optionsPrinter(this);
                    selectOption();
                } else if (Integer.parseInt(key) == 1) {
                    printer.playerOptionsPrinter();
                    printer.optionsPrinter(this);
                    selectOption();
                } else if (Integer.parseInt(key) == 2) {
                    printer.printGameBoardSettings();
                    printer.optionsPrinter(this);
                    selectOption();
                } else if (Integer.parseInt(key) == 3) {
                    processor.processGame();
                }
                return;
            } else {
                printer.incorrectSelectionMessage();
            }
        }
    }
}
