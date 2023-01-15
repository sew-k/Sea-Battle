package com.kodilla.seabattle.data;

import com.kodilla.seabattle.presentation.Keyboard;
import com.kodilla.seabattle.presentation.Printer;

import java.util.*;

public class ComputerPlayer extends Player {

    private final String name = "Computer";
    private List<Ship> ships = new ArrayList<>();
    private Set<String> shots = new HashSet<>();

    @Override
    public List<Ship> getShips() {
        return ships;
    }
    @Override
    public void addShot(String shot) {
        this.shots.add(shot);
    }
    @Override
    public void setShips(List<Ship> ships) {
        this.ships = ships;
    }

    @Override
    public void addShip(Ship ship) {
        this.ships.add(ship);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Set<String> getShots() {
        return shots;
    }

    @Override
    public String selectTarget() {
        Printer printer = new Printer();
        Board board = new Board();
        Random randomRow = new Random();
        Random randomColumn = new Random();
        String target = (board.getColumns().get(randomColumn.nextInt(board.getColumns().size())) +
                board.getRows().get(randomRow.nextInt(board.getRows().size())));
        printer.printTarget(target);
        return target;
    }
}
