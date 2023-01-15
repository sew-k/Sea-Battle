package com.kodilla.seabattle.logic;

import com.kodilla.seabattle.data.Board;

public class Validator {

    public boolean validateForOptions(int input, Options options) {
        boolean result = false;
        if ((input >= 0) && (input <= (options.getOptions().size()-1))) {
            result = true;
        }
        return result;
    }

    public boolean validateIsTargetOnBoard(String target, Board board) {
        if ((target.length() == 2) &&
                (board.getColumns().contains(Character.toString(target.charAt(0)))) &&
                (board.getRows().contains(Character.toString(target.charAt(1))))) {
            return true;
        } else if ((target.length() == 3) &&
                (board.getColumns().contains(Character.toString(target.charAt(0)))) &&
                (board.getRows().contains(Character.toString(target.charAt(1)) + Character.toString(target.charAt(2))))) {
            return true;
        } else {
            return false;
        }
    }
}
