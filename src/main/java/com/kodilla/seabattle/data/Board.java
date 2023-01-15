package com.kodilla.seabattle.data;

import java.util.ArrayList;
import java.util.List;

public class Board {

    private static int rowsCount = 5;
    private static int columnsCount = 5;

    private  List<String> rows = new ArrayList<>();
    private  List<String> columns = new ArrayList<>();

    public Board() {

        for (int i = 1; i<= rowsCount; i++) {
            rows.add(Integer.toString(i));
        }
        for (int j = 0; j< columnsCount; j++) {
            String alphabet = "abcdefghijklmnopqrstuvwxyz";
            columns.add(Character.toString(alphabet.charAt(j)));
        }
    }

    public static int getRowsCount() {
        return rowsCount;
    }

    public static int getColumnsCount() {
        return columnsCount;
    }

    public  List<String> getRows() {
        return rows;
    }

    public  List<String> getColumns() {
        return columns;
    }
}
