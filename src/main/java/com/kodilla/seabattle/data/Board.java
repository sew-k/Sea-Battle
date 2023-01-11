package com.kodilla.seabattle.data;

import java.util.ArrayList;
import java.util.List;

public class Board {

    private int rows;
    private int columns;

    private List<String> row = new ArrayList<>();
    private List<String> column = new ArrayList<>();

    public Board(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
    }
}
