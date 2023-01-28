package com.kodilla.seabattle.logic;


import com.kodilla.seabattle.*;
import com.kodilla.seabattle.data.*;
import com.kodilla.seabattle.presentation.Printer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;
import java.util.stream.Collectors;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class GameProcessorTestSuite {

    @Test
    void testStartGame() {
        //Given
        GameProcessor mockGameProcessor = mock(GameProcessor.class);
        GameProcessor gameProcessor = new GameProcessor();
        Player player1 = new HumanPlayer();
        Player player2 = new ComputerPlayer();
        Board.setColumnsCount(1);
        Board.setRowsCount(1);
        Board board = new Board();
        Printer mockPrinter = mock(Printer.class);

        //When
        doNothing().when(mockGameProcessor).startGame();
        //gameProcessor.startGame();

        //Then
        verify(mockGameProcessor,times(1)).processGame();
        verify(mockGameProcessor,times(1)).singleRoundProcessor(player1,player2);
    }

    @Test
    void testSingleRoundProcessorIfSingleTurnGivesFalseMeansExitGame() {
        //Given
        Player player1 = new HumanPlayer();
        Player player2 = new ComputerPlayer();
        GameProcessor mockGameProcessor = mock(GameProcessor.class);
        when(mockGameProcessor.singleTurnProcessor(player1,player2)).thenReturn(false);
        when(mockGameProcessor.singleTurnProcessor(player2,player1)).thenReturn(false);

        //When
        boolean result1 = mockGameProcessor.singleTurnProcessor(player1,player2);
        boolean result2 = mockGameProcessor.singleTurnProcessor(player2,player1);

        //Then
        Assertions.assertFalse(result1);
        Assertions.assertFalse(result2);
    }

    @Test
    void testSingleRoundProcessorIfSingleTurnGivesTrueMeansContinueGame() {
        //Given
        Player player1 = new HumanPlayer();
        Player player2 = new ComputerPlayer();
        GameProcessor mockGameProcessor = mock(GameProcessor.class);
        when(mockGameProcessor.singleTurnProcessor(player1,player2)).thenReturn(true);
        when(mockGameProcessor.singleTurnProcessor(player2,player1)).thenReturn(true);

        //When
        boolean result1 = mockGameProcessor.singleTurnProcessor(player1,player2);
        boolean result2 = mockGameProcessor.singleTurnProcessor(player2,player1);

        //Then
        Assertions.assertTrue(result1);
        Assertions.assertTrue(result2);
    }

    @Test
    void testSingleTurnProcessorIfGameExit() {
        //Given
        GameProcessor mockGameProcessor = mock(GameProcessor.class);
        GameProcessor gameProcessor = new GameProcessor();
        Player player1 = new HumanPlayer();
        player1.setName("aaa");
        Player player2 = new ComputerPlayer();
        PlayerTurnOptions mockPlayerTurnOptions = mock(PlayerTurnOptions.class);

        when(mockPlayerTurnOptions.singleRoundSelectOption(player1,player2,mockGameProcessor)).thenReturn(false);

        //When
        boolean result = mockGameProcessor.singleTurnProcessor(player1,player2);

        //Then
        Assertions.assertFalse(result);
    }

    @Test
    void testSingleTurnProcessorIfContinueGame() {
        //Given
        GameProcessor mockGameProcessor = mock(GameProcessor.class);
        Player player1 = new HumanPlayer();
        player1.setName("aaa");
        Player player2 = new ComputerPlayer();
        PlayerTurnOptions mockPlayerTurnOptions = mock(PlayerTurnOptions.class);

        when(mockPlayerTurnOptions.singleRoundSelectOption(player1,player2,mockGameProcessor)).thenReturn(true);

        //When
        boolean result = mockGameProcessor.singleTurnProcessor(player1,player2);

        //Then
        Assertions.assertTrue(result);
    }

    @Test
    void testPlayerWinGame() {
        //Given
        GameProcessor gp = new GameProcessor();
        Player player = new HumanPlayer();
        player.setName("aaa");
        Printer printer = new Printer();

        //When
        player.setScore(1);
        gp.playerWinGame(player);
        Map<String,Integer> testScoreBoard = ScoreBoard.getScoreMap();
        printer.showScoreBoard();
        List<String> resultNames = testScoreBoard.entrySet().stream().map(e->e.getKey()).collect(Collectors.toList());
        String resultName = resultNames.get(0);
        int resultScore = testScoreBoard.entrySet().stream().map(v->v.getValue()).toList().get(0);

        //Then
        Assertions.assertEquals(1,resultScore);
        Assertions.assertEquals(player.getName(),resultName);
    }

    @Test
    void testWinnerOfBattleCheckPlayerWinGame() {
        //Given
        GameProcessor gameProcessor = new GameProcessor();
        Player winner = new HumanPlayer();
        winner.setName("winner");
        Ship winnerShip = new Ship();
        Map<String,String> winnerShipStatus = new HashMap<>();
        winnerShipStatus.put("a1", "good");
        winnerShip.setStatusOnBoard(winnerShipStatus);
        winner.setShips(List.of(winnerShip));

        Player loser = new HumanPlayer();
        loser.setName("loser");
        Ship loserShip = new Ship();
        Map<String,String> loserShipStatus = new HashMap<>();
        loserShipStatus.put("a1", "good");
        loserShip.setStatusOnBoard(loserShipStatus);
        loser.setShips(List.of(loserShip));

        //When
        loserShipStatus.replace("a1","sink");
        Player secondPlayerLoser = gameProcessor.winnerOfBattleCheck(winner,loser);
        Player firstPlayerLoser = gameProcessor.winnerOfBattleCheck(loser,winner);

        //Then
        Assertions.assertEquals(winner,secondPlayerLoser);
        Assertions.assertEquals(winner,firstPlayerLoser);
    }
    @Test
    void testWinnerOfBattleCheckNoOneWinYet() {
        //Given
        GameProcessor gameProcessor = new GameProcessor();
        Player winnerMaybe = new HumanPlayer();
        winnerMaybe.setName("winnerMaybe");
        Ship winnerMaybeShip = new Ship();
        Map<String,String> winnerMaybeShipStatus = new HashMap<>();
        winnerMaybeShipStatus.put("a1", "good");
        winnerMaybeShip.setStatusOnBoard(winnerMaybeShipStatus);
        winnerMaybe.setShips(List.of(winnerMaybeShip));

        Player loserMaybe = new HumanPlayer();
        loserMaybe.setName("loserMaybe");
        Ship loserMaybeShip = new Ship();
        Map<String,String> loserMaybeShipStatus = new HashMap<>();
        loserMaybeShipStatus.put("a1", "good");
        loserMaybeShip.setStatusOnBoard(loserMaybeShipStatus);
        loserMaybe.setShips(List.of(loserMaybeShip));

        //When
        Player secondPlayerLoser = gameProcessor.winnerOfBattleCheck(winnerMaybe,loserMaybe);
        Player firstPlayerLoser = gameProcessor.winnerOfBattleCheck(loserMaybe,winnerMaybe);

        //Then
        Assertions.assertEquals(null,secondPlayerLoser);
        Assertions.assertEquals(null,firstPlayerLoser);
    }

    @Test
    void testProcessGame() {
        //Given
        GameProcessor mockGameProcessor = mock(GameProcessor.class);
        Printer mockPrinter = mock(Printer.class);
        Menu mockMenu = mock(Menu.class);
        Menu menu = new Menu();

        //When
        doNothing().when(mockGameProcessor).processGame();

        //Then
        verify(mockPrinter,times(1)).titlePrinter();
        verify(mockPrinter,times(1)).optionsPrinter(menu);
        verify(mockMenu,times(1)).selectOption();
    }
}
