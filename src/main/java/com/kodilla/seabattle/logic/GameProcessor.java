package com.kodilla.seabattle.logic;

import com.kodilla.seabattle.data.*;
import com.kodilla.seabattle.presentation.Printer;
import com.kodilla.seabattle.presentation.Keyboard;

import java.util.Map;

public class GameProcessor {

    private Player playerOne;
    private Player playerTwo;



    public void exitGame() {
        //temporarily
        System.out.println("exit game");

    }


    public void startGame() {

        //temporarily
        System.out.println("start game");

        Printer printer = new Printer();
        Keyboard keyboard = new Keyboard();

        printer.boardDrawer();
        Player humanPlayer = new HumanPlayer();
        printer.askForPlayerName();
        humanPlayer.setName(keyboard.getString());

        System.out.println("your name is: " + humanPlayer.getName());

        setPlayerOne(humanPlayer);
        printer.askForSetShips(playerOne);
        Ship ship2 = new Ship();
        playerOne.addShip(ship2);
        System.out.println(ship2.getSize());
        System.out.println(ship2.getStatusOnBoard());

        setPlayerTwo(new ComputerPlayer());
        Ship ship3 = new Ship();
        playerTwo.addShip(ship3);

        //printer.playersBoardDrawer(playerOne);
        //printer.playersBoardDrawer(playerTwo);

        boolean battleEnd = false;

        while (!battleEnd) {
            singleRoundProcessor();
            Player winner = winnerOfBattleCheck(playerOne, playerTwo);
            if (winner != null) {
                battleEnd = true;
            }
        }
    }

    public void singleRoundProcessor() {

        singleShotProcessor(playerOne, playerTwo);
        singleShotProcessor(playerTwo, playerOne);

    }

    public void singleShotProcessor(Player attacker, Player defender) {
        Printer printer = new Printer();
        Validator validator = new Validator();
        Board board = new Board();
        String target;
        printer.askForTarget(attacker);
        target = attacker.selectTarget();

        if (validator.validateIsTargetOnBoard(target, board)) {
            attacker.addShot(target);
            for (Ship ship : defender.getShips()) {
                ship.getStatusOnBoard().replace(target,"hit");
                ship.checkIfShipSink();
            }

            //temporarily
            printer.printPlayerShots(attacker);
            printer.printPlayerShips(defender);

        }

        Player winner = null;
        winner = winnerOfBattleCheck(playerOne,playerTwo);
        if (winner != null) {
            winner.setScore(1);
            playerWinGame(winner);
        }

    }

    public Player winnerOfBattleCheck(Player playerOne, Player playerTwo) {

        boolean playerOneAllShipsSunk = playerOne.getShips().stream()
                .allMatch(ship -> ship.getStatusOnBoard().containsValue("sink"));
        boolean playerTwoAllShipsSunk = playerTwo.getShips().stream()
                .allMatch(ship -> ship.getStatusOnBoard().containsValue("sink"));

        if (playerTwoAllShipsSunk) {
            return playerOne;
        }
        if (playerOneAllShipsSunk) {
            return playerTwo;
        } else {
            return null;
        }
    }

    public void playerWinGame(Player winner) {
        ScoreBoard.addScore(winner.getName(), winner.getScore());
        Printer printer = new Printer();
        printer.printWinner(winner);
        processGame();
    }

    public void processGame() {
        Printer printer = new Printer();
        Menu menu = new Menu();
        printer.titlePrinter();
        printer.titleOfChoicesPrinter(menu);
        printer.optionsPrinter(menu);
        menu.selectOption();
    }

    public Player getPlayerOne() {
        return playerOne;
    }

    public void setPlayerOne(Player playerOne) {
        this.playerOne = playerOne;
    }

    public Player getPlayerTwo() {
        return playerTwo;
    }

    public void setPlayerTwo(Player playerTwo) {
        this.playerTwo = playerTwo;
    }
}
