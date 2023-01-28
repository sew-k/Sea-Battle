package com.kodilla.seabattle.logic;

import com.kodilla.seabattle.data.Player;
import com.kodilla.seabattle.presentation.Keyboard;
import com.kodilla.seabattle.presentation.Printer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PlayerTurnOptions extends Options {
    private final String optionsTitle = "Player's turn options";
    private final List<String> options = new ArrayList<>(Arrays.asList("Shot","List of shots","Status of my ships",
            "Show my game board","Show hostile game board", "Exit battle"));

    @Override
    public List<String> getOptions() {
        return options;
    }

    @Override
    public String getOptionsTitle() {
        return optionsTitle;
    }


    public boolean singleRoundSelectOption(Player playerOne, Player playerTwo, GameProcessor processor) {

        super.selectOption();
        Keyboard keyboard = new Keyboard();
        Validator validator = new Validator();
        Printer printer = new Printer();

        boolean end = false;

        while (!end) {
            printer.optionsPrinter(this);
            printer.askForSelect();
            String key = keyboard.getString();
            if (validator.validateForOptions(key, this)) {
                if (Integer.parseInt(key) == 0) {
                    processor.singleShotProcessor(playerOne, playerTwo);
                    return true;
                } else if (Integer.parseInt(key) == 1) {
                    printer.printPlayerShots(playerOne);
                } else if (Integer.parseInt(key) == 2) {
                    printer.printPlayerShips(playerOne);
                } else if (Integer.parseInt(key) == 3) {
                    printer.playersBoardDrawer(playerOne);
                } else if (Integer.parseInt(key) == 4) {
                    printer.hostileBoardDrawer(playerOne,playerTwo);
                } else if (Integer.parseInt(key) == 5) {
                    end = true;
                    return false;
                }
            } else {
                printer.incorrectSelectionMessage();
            }
        }
        return true;
    }
}
