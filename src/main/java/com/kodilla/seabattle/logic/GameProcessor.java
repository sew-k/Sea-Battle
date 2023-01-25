package com.kodilla.seabattle.logic;

import com.kodilla.seabattle.data.*;
import com.kodilla.seabattle.presentation.Printer;
import com.kodilla.seabattle.presentation.Keyboard;

public class GameProcessor {

    private Player playerOne;
    private Player playerTwo;

    public void exitGame() {
        Printer printer = new Printer();
        printer.printExitGame();
    }

    public void startGame() {

        Printer printer = new Printer();
        Keyboard keyboard = new Keyboard();
        Settings settings = new Settings();

        printer.boardDrawer();
        //settings.setDefaultShipCountSettings();

        Player humanPlayer = new HumanPlayer();
        printer.askForPlayerName();
        humanPlayer.setName(keyboard.getString());

        setPlayerOne(humanPlayer);
        setPlayerTwo(new ComputerPlayer());

        printer.askForSetShips(playerOne);
        playerOne.shipsSetUp();

        printer.askForSetShips(playerTwo);
        playerTwo.shipsSetUp();

        printer.playersBoardDrawer(playerOne);
        printer.playersBoardDrawer(playerTwo);

        boolean battleEnd = false;
        while (!battleEnd) {
            battleEnd = !singleRoundProcessor(playerOne, playerTwo);
            Player winner = winnerOfBattleCheck(playerOne, playerTwo);
            if (winner != null) {
                battleEnd = true;
            }
        }
    }

    public boolean singleRoundProcessor(Player playerOne, Player playerTwo) {

        if ((singleTurnProcessor(playerOne,playerTwo)) == false) {
            return false;
        }
        if ((singleTurnProcessor(playerTwo,playerOne)) == false)  {
            return false;
        } else
            return true;
    }

    public boolean singleTurnProcessor(Player currentPlayer, Player otherPlayer) {
        PlayerTurnOptions playerTurnOptions = new PlayerTurnOptions();
        if (currentPlayer.getName().equals("Computer")) {
            singleShotProcessor(currentPlayer, otherPlayer);
        } else {
            if (playerTurnOptions.singleRoundSelectOption(currentPlayer,otherPlayer,this) == false) {
                return false;
            }
        }
        return true;
    }

    public void singleShotProcessor(Player attacker, Player defender) {
        Printer printer = new Printer();
        Validator validator = new Validator();
        Board board = new Board();
        String target;
        printer.whoseTurnInformation(attacker);
        printer.hostileBoardDrawer(attacker, defender);
        printer.askForTarget(attacker);
        target = attacker.selectTarget();
        boolean shotFired = false;
        while (!shotFired) {
            if (validator.validateIsTargetOnBoard(target, board)) {
                attacker.addShot(target);
                for (Ship ship : defender.getShips()) {
                    ship.getStatusOnBoard().replace(target, "hit");
                    ship.checkIfShipSink();
                }
                shotFired = true;
            } else {
                printer.targetOutOfBoardMessage();
                printer.askForTarget(attacker);
                target = attacker.selectTarget();
            }
        }
        printer.hostileBoardDrawer(attacker, defender);
        Player winner;
        Player loser;
        winner = winnerOfBattleCheck(playerOne,playerTwo);
        if (winner != null) {
            winner.setScore(1);
            if (winner.equals(playerOne)) {
                loser = playerTwo;
            } else {
                loser = playerOne;
            }
            printer.playersBoardDrawer(loser);
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
