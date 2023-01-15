package com.kodilla.seabattle.presentation;

import com.kodilla.seabattle.data.Board;
import com.kodilla.seabattle.data.Player;
import com.kodilla.seabattle.data.ScoreBoard;
import com.kodilla.seabattle.data.Ship;
import com.kodilla.seabattle.logic.Options;
import com.kodilla.seabattle.logic.Settings;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class Printer {

    public void optionsPrinter(Options options) {
        System.out.println("----------------------------");
        System.out.println(options.getOptionsTitle());
        for (String choicePosition : options.getOptions()) {
            System.out.println("[" + options.getOptions().indexOf(choicePosition) + "] - " + choicePosition);
        }
    }

    public void titlePrinter() {
        System.out.println("============================");
        System.out.println("SEA BATTLE");
        System.out.println("----------------------------");
    }

    public void askForTarget(Player player) {
        System.out.print(player.getName() + ", please select your target on the board: ");
    }
    public void printTarget(String target) {
        System.out.println(target);
    }


    public void showScoreBoard() {
        System.out.println("----------------------------");
        System.out.println("SCORE BOARD");
        for (Map.Entry<String,Integer> entry : ScoreBoard.getScoreMap().entrySet()) {
            System.out.println(entry.getKey() + "  -  " + entry.getValue());
        }
        System.out.println("----------------------------");
    }

    public void printShipCountSettings(Map<Integer,Integer> shipCountSettings) {
        for (Map.Entry<Integer,Integer> entry : shipCountSettings.entrySet()) {
            String shipSize = "";
            for (int i=0; i< entry.getKey(); i++) {
                shipSize = shipSize + "[]";
            }
            System.out.println("Quantity of " + shipSize + " is: " + entry.getValue());
        }

    }

    public void playerOptionsPrinter() {
        System.out.println("One Player game: " + Settings.isOnePlayerGame());
        System.out.println("Two Player game: " + !Settings.isOnePlayerGame());
    }

    public void printGameBoardSettings() {
        System.out.println("Size of game board:");
        System.out.println("- columns: " + Board.getColumnsCount());
        System.out.println("- rows: " + Board.getRowsCount());
    }

    public void askForSelect() {
        System.out.print("Please select an option: ");
    }
    public void askForPlayerName() {
        System.out.print("Please enter your name: ");
    }
    public void askForSetShips(Player player) {
        System.out.println(player.getName() + ", please set up your ships.");
    }

    public void incorrectSelectionMessage() {
        System.out.print("Incorrect selection. Please, select again: ");
    }

    public void targetOutOfBoardMessage() {
        System.out.println("target out of board!");
    }

    public void boardDrawer() {

        System.out.println("Board of the game - your battlefield");
        Board board = new Board();
        System.out.print("  ");
        for (int i = 0; i< board.getColumns().size(); i++) {
            System.out.print("  " + board.getColumns().get(i));
        }
        System.out.println("");
        for (int r = 0; r< board.getRowsCount(); r++) {
            String spacing = "   ";
            int rowNumber = Integer.parseInt(board.getRows().get(r))+1;
            if (rowNumber >= 10) {
                spacing = "  ";
            }
            System.out.print(rowNumber + spacing);
            for (int j = 0; j < board.getColumns().size(); j++) {
                System.out.print(".  ");
            }
            System.out.print("\n");
        }
    }

    public void printPlayerShots(Player player) {
        System.out.println("List of shots player '" + player.getName() + "':");
        for (String shot : player.getShots()) {
            System.out.println(shot);
        }
    }

    public void printPlayerShips(Player player) {
        System.out.println("List of ships player '" + player.getName() + "':");
        player.getShips().stream()
                .flatMap(ship -> ship.getStatusOnBoard().entrySet().stream())
                .forEach(System.out::println);
    }

    public void printWinner(Player winner) {
        System.out.println("Player " + winner.getName() + " win the game!");
    }

    public void playersBoardDrawer(Player player) {
        System.out.println("Board of the game - " + player.getName() + "'s battlefield");
        List<Ship> playerShips = player.getShips();

        Board playersBoard = new Board();
        System.out.print("  ");
        for (int i = 0; i< playersBoard.getColumns().size(); i++) {
            System.out.print("  " + playersBoard.getColumns().get(i));
        }
        System.out.println("");
        for (int r = 0; r< playersBoard.getRowsCount(); r++) {
            String spacing = "   ";
            int rowNumber = Integer.parseInt(playersBoard.getRows().get(r));
            if (rowNumber >= 10) {
                spacing = "  ";
            }
            System.out.print(rowNumber + spacing);
            for (int j = 0; j < playersBoard.getColumns().size(); j++) {
                String field = ".";
                String coordinates;
                coordinates = playersBoard.getColumns().get(j) + playersBoard.getRows().get(r);
                for (Ship ship : playerShips) {
                    if (ship.getStatusOnBoard().get(coordinates) != null) {
                        field = Character.toString(ship.getStatusOnBoard().get(coordinates).toUpperCase().charAt(0));
                    }
                }
                System.out.print(field + "  ");
            }
            System.out.print("\n");
        }
    }

    public void hostileBoardDrawer(Player attacker, Player hostile) {
        System.out.println("Board of the game - " + hostile.getName() + "'s battlefield");
        List<Ship> playerShips = hostile.getShips();
        Set<String> attackerShots = attacker.getShots();

        Board playersBoard = new Board();
        System.out.print("  ");
        for (int i = 0; i< playersBoard.getColumns().size(); i++) {
            System.out.print("  " + playersBoard.getColumns().get(i));
        }
        System.out.println("");
        for (int r = 0; r< playersBoard.getRowsCount(); r++) {
            String spacing = "   ";
            int rowNumber = Integer.parseInt(playersBoard.getRows().get(r));
            if (rowNumber >= 10) {
                spacing = "  ";
            }
            System.out.print(rowNumber + spacing);
            for (int j = 0; j < playersBoard.getColumns().size(); j++) {
                String field = ".";
                String coordinates;
                coordinates = playersBoard.getColumns().get(j) + playersBoard.getRows().get(r);
                for (String shot : attackerShots) {
                    if (attackerShots.contains(coordinates)) {
                        field = "x";
                    }

                }
                for (Ship ship : playerShips) {
                    if (ship.getStatusOnBoard().get(coordinates) != null) {
                        field = Character.toString(ship.getStatusOnBoard().get(coordinates).toUpperCase().charAt(0));
                        if (field.equals("G")) {
                            field = ".";
                        }
                    }
                }
                System.out.print(field + "  ");
            }
            System.out.print("\n");
        }
    }
}
