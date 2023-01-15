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


    public void singleRoundSelectOption(Player playerOne, Player playerTwo, GameProcessor processor) {

        super.selectOption();
        Keyboard keyboard = new Keyboard();
        Validator validator = new Validator();
        Printer printer = new Printer();

        boolean incorrect = false;

        while (!incorrect) {
            int key = keyboard.getInt();
            if (validator.validateForOptions(key, this)) {
                if (key == 0) {
                    processor.singleShotProcessor(playerOne, playerTwo);
                } else if (key == 1) {
                    printer.printPlayerShots(playerOne);
                    printer.optionsPrinter(this);
                    singleRoundSelectOption(playerOne, playerTwo, processor);
                } else if (key == 2) {
                    printer.printPlayerShips(playerOne);
                    printer.optionsPrinter(this);
                    singleRoundSelectOption(playerOne, playerTwo, processor);
                } else if (key == 3) {
                    printer.playersBoardDrawer(playerOne);
                    printer.optionsPrinter(this);
                    singleRoundSelectOption(playerOne, playerTwo, processor);
                } else if (key == 4) {
                    printer.hostileBoardDrawer(playerOne,playerTwo);
                    printer.optionsPrinter(this);
                    singleRoundSelectOption(playerOne, playerTwo, processor);
                } else if (key == 5) {
                    processor.processGame();
                }
                return;
            } else {
                printer.incorrectSelectionMessage();
            }
        }
    }
}
