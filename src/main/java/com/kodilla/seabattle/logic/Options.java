package com.kodilla.seabattle.logic;

import com.kodilla.seabattle.presentation.Printer;

import java.util.List;

public abstract class Options {

    private List<String> options;
    private String choicesTitle;

    public List<String> getOptions() {
        return options;
    }

    public String getChoicesTitle() {
        return choicesTitle;
    }

    public void selectOption() {

        Printer printer = new Printer();
        printer.askForSelect();
    }

}
