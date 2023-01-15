package com.kodilla.seabattle.data;

import com.kodilla.seabattle.presentation.Printer;

import java.util.*;

public class Ship {

    private int size;
    private Map<String,String> statusOnBoard = new HashMap<>();


    public Ship() {
        //temporarily
        //Ship ship = new Ship();

        this.size = 2;
        Map<String,String> statusOnBoard = new HashMap<>();
        statusOnBoard.put("b2","good");
        statusOnBoard.put("b3", "good");
        this.statusOnBoard = statusOnBoard;

    }

    public int getSize() {
        return size;
    }

    public Map<String, String> getStatusOnBoard() {
        return statusOnBoard;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Ship ship = (Ship) o;

        if (size != ship.size) return false;
        return Objects.equals(statusOnBoard, ship.statusOnBoard);
    }

    @Override
    public int hashCode() {
        int result = size;
        result = 31 * result + (statusOnBoard != null ? statusOnBoard.hashCode() : 0);
        return result;
    }

    public void checkIfShipSink() {
        boolean shipSink = true;
        for (Map.Entry<String,String> entry : getStatusOnBoard().entrySet()) {
            if (entry.getValue().equals("good")) {
                shipSink = false;
            }
        }
        if (shipSink) {
            for (Map.Entry<String,String> entry : getStatusOnBoard().entrySet()) {
                entry.setValue("sink");
            }
        }
    }
}
