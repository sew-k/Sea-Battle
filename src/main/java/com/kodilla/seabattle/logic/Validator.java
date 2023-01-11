package com.kodilla.seabattle.logic;

public class Validator {

    public boolean validateForMenu(int input) {
        boolean result = false;
        Menu menu = new Menu();
        if ((input >= 0) && (input <= (menu.getOptions().size()-1))) {
            result = true;
        }
        return result;
    }
}
