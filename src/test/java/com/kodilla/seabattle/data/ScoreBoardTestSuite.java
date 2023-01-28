package com.kodilla.seabattle.data;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ScoreBoardTestSuite {

    @Test
    void testScoreBoardAddScoreWhenBoardEmpty() {
        //Given
        Player player1 = new HumanPlayer();
        player1.setName("PlayerOne");
        player1.setScore(1);

        //When
        ScoreBoard.addScore(player1.getName(), player1.getScore());

        //Then
        Assertions.assertTrue(ScoreBoard.getScoreMap().containsKey("PlayerOne"));
        Assertions.assertEquals(1, ScoreBoard.getScoreMap().get("PlayerOne"));
    }
    @Test
    void testScoreBoardAddScoreWhenPlayerSecondWin() {
        //Given
        Player player2 = new HumanPlayer();
        player2.setName("PlayerTwo");
        player2.setScore(1);
        ScoreBoard.addScore(player2.getName(), player2.getScore());

        //When
        ScoreBoard.addScore(player2.getName(), player2.getScore());

        //Then
        Assertions.assertTrue(ScoreBoard.getScoreMap().containsKey("PlayerTwo"));
        Assertions.assertEquals(2, ScoreBoard.getScoreMap().get("PlayerTwo"));
    }
}
