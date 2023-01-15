package com.kodilla.seabattle.logic;

import com.kodilla.seabattle.data.Board;

public class Validator {

    public boolean validateForChoices(int input, Options options) {
        boolean result = false;
        if ((input >= 0) && (input <= (options.getOptions().size()-1))) {
            result = true;
        }
        return result;
    }

    public boolean validateIsTargetOnBoard(String target, Board board) {

        //temporarily rows only one char
        if ((target.length() == 2) &&
                (board.getColumns().contains(Character.toString(target.charAt(0)))) &&
                (board.getRows().contains(Character.toString(target.charAt(1))))) {
            System.out.println("target at board");
            return true;

        } else if ((target.length() == 3) &&
                (board.getColumns().contains(Character.toString(target.charAt(0)))) &&
                (board.getRows().contains(Character.toString(target.charAt(1)) + Character.toString(target.charAt(2))))) {
            System.out.println("target at board - two char");
            return true;

        } else {
            System.out.println("target out of board");
            return false;
        }
    }
}
