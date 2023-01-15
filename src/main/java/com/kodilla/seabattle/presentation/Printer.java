package com.kodilla.seabattle.presentation;

import com.kodilla.seabattle.data.Board;
import com.kodilla.seabattle.data.Player;
import com.kodilla.seabattle.data.ScoreBoard;
import com.kodilla.seabattle.data.Ship;
import com.kodilla.seabattle.logic.Options;
import com.kodilla.seabattle.logic.Settings;

import java.util.List;
import java.util.Map;

public class Printer {

    public void optionsPrinter(Options options) {
        for (String choicePosition : options.getOptions()) {
            System.out.println("[" + options.getOptions().indexOf(choicePosition) + "] - " + choicePosition);
        }
    }

    public void titlePrinter() {
        System.out.println("");
        System.out.println("SEA BATTLE");
        System.out.println("");
    }

    public void askForTarget(Player player) {
        System.out.print(player.getName() + ", please select your target on the board:");
    }

    public void showScoreBoard() {
        System.out.println("---------------------");
        System.out.println("SCORE BOARD");
        for (Map.Entry<String,Integer> entry : ScoreBoard.getScoreMap().entrySet()) {
            System.out.println(entry.getKey() + "  -  " + entry.getValue());
        }
        System.out.println("---------------------");
    }

    public void titleOfChoicesPrinter(Options options) {
        System.out.println("--------------------------");
        System.out.println(options.getChoicesTitle());
    }

    public void askForSelect() {
        System.out.print("Please select an option: ");
    }
    public void askForPlayerName() {
        System.out.print("Please enter your name: ");
    }
    public void askForSetShips(Player player) {
        System.out.print(player.getName() + ", please set up your ships.");
    }

    public void incorrectSelectionMessage() {
        System.out.print("Incorrect selection. Please, select again: ");
    }

    public void boardDrawer() {
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

    public void settingsPrinter(Settings settings) {
        for (String setting : settings.getOptions()) {
            System.out.println("[" + settings.getOptions().indexOf(setting) + "] - " + setting);
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
        System.out.println("Player " + winner.getName() + " win game");
    }

    public void playersBoardDrawer(Player player) {
        System.out.println("Player '" + player.getName() + "'");
        List<Ship> playersShips = player.getShips();

        Board playersBoard = new Board();
        System.out.print("  ");
        for (int i = 0; i< playersBoard.getColumns().size(); i++) {
            System.out.print("  " + playersBoard.getColumns().get(i));
        }
        System.out.println("");
        for (int r = 0; r< playersBoard.getRowsCount(); r++) {
            String spacing = "   ";
            int rowNumber = Integer.parseInt(playersBoard.getRows().get(r))+1;
            if (rowNumber >= 10) {
                spacing = "  ";
            }
            System.out.print(rowNumber + spacing);
            for (int j = 0; j < playersBoard.getColumns().size(); j++) {
                String field = "x";

                // to implement printing fields with existing ships

                System.out.print(field + "  ");
            }
            System.out.print("\n");
        }
    }
}
