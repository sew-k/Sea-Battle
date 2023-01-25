package com.kodilla.seabattle.data;

import com.kodilla.seabattle.logic.Settings;
import com.kodilla.seabattle.logic.Validator;
import com.kodilla.seabattle.presentation.Keyboard;
import com.kodilla.seabattle.presentation.Printer;

import java.util.*;

public class ComputerPlayer extends Player {

    private final String name = "Computer";
    private List<Ship> ships = new ArrayList<>();
    private Set<String> shots = new HashSet<>();
    private Set<String> availableFieldsOnBoard = new HashSet<>();
    @Override
    public Set<String> getAvailableFieldsOnBoard() {
        return availableFieldsOnBoard;
    }
    public Set<String> getAvailableFieldsForShipSetUp(Ship ship) {
        return super.getAvailableFieldsForShipSetUp(ship);
    }

    public void setAvailableFieldsOnBoard() {
        this.availableFieldsOnBoard = checkAvailableFieldsOnPlayerBoard();
    }

    public Set<String> checkAvailableFieldsOnPlayerBoard() {
        return super.checkAvailableFieldsOnPlayerBoard();
    }

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
        String target = "";
        boolean inCorrectTarget = true;
        while (inCorrectTarget) {
            target = (board.getColumns().get(randomColumn.nextInt(board.getColumns().size())) +
                    board.getRows().get(randomRow.nextInt(board.getRows().size())));
            printer.printTarget(target);
            if (!getShots().contains(target)) {
                addShot(target);
                inCorrectTarget = false;
            }
        }
        return target;
    }

    @Override
    public void shipsSetUp() {

        Printer printer = new Printer();
        Keyboard keyboard = new Keyboard();
        Validator validator = new Validator();
        Random random = new Random();
        Board board = new Board();
        printer.printShipCountSettings(Settings.getShipCountSettings());
        Map<Integer,Integer> shipCountSettings = Settings.getShipCountSettings();

        setAvailableFieldsOnBoard();
//        System.out.println("available fields: " + getAvailableFieldsOnBoard());
//        System.out.println("all fields: " + board.getAllFieldsOnBoard());

        for (Map.Entry<Integer,Integer> entry : shipCountSettings.entrySet()) {
            for (int j=0; j< entry.getValue(); j++) {
                Ship ship = new Ship();
                ship.setSize(entry.getKey());
                Map<String,String> shipStatus = new HashMap<>();

                printer.askForSetUpShip(ship);

                boolean shipAdded = false;
                while (!shipAdded) {
                    for (int i = 0; i < entry.getKey(); i++) {    // iterate field of ship to set up

                        if (i == 0) {
                            printer.askForField(this);

                        } else if (i > 0) {
                            printer.askForNextField(this);

                        }
                        List<String> availableFieldsForShipSetUp = new ArrayList<>();
                        availableFieldsForShipSetUp = getAvailableFieldsForShipSetUp(ship).stream()
                                .filter(c->getAvailableFieldsOnBoard().contains(c))
                                .toList();

                        //printer.printTarget("available fields for ship: " + availableFieldsForShipSetUp);

                        String temporaryField = availableFieldsForShipSetUp.get(random.nextInt(0,availableFieldsForShipSetUp.size()));

                        shipStatus = ship.getStatusOnBoard();
                        shipStatus.put(temporaryField, "good");

                        ship.setStatusOnBoard(shipStatus);
                        ship.setBufferZone();
                    }
                    addShip(ship);
                    ship.setBufferZone();
                    setAvailableFieldsOnBoard();
                    shipAdded = true;
                }
                printer.printShipAdded(ship);
                printer.printShipBufferZone(ship);
                printer.playersBoardDrawer(this);
                //printer.printPlayerShips(this);
                //System.out.println("available fields: " + getAvailableFieldsOnBoard());
            }
        }
    }

}
