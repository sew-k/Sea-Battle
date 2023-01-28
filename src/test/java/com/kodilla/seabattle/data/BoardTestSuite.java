package com.kodilla.seabattle.data;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

public class BoardTestSuite {

    @Test
    void testGetAllFieldsOnBoard() {
        //Given
        Board.setColumnsCount(2);
        Board.setRowsCount(2);
        Board board = new Board();

        //When
        Set<String> boardFieldsSet = board.getAllFieldsOnBoard();
        int numberOfBoardFields = boardFieldsSet.size();

        //Then
        Assertions.assertEquals(4,numberOfBoardFields);
        Assertions.assertTrue(boardFieldsSet.contains("a1"));
        Assertions.assertTrue(boardFieldsSet.contains("a2"));
        Assertions.assertTrue(boardFieldsSet.contains("b1"));
        Assertions.assertTrue(boardFieldsSet.contains("b2"));
        Assertions.assertFalse(boardFieldsSet.contains("b3"));
        Assertions.assertFalse(boardFieldsSet.contains("a3"));
    }
    @Test
    void testAllAroundFieldsOfBufferZoneIfAvailable() {
        //Given
        Board.setColumnsCount(2);
        Board.setRowsCount(2);
        Board board = new Board();
        String field = "a1";

        //When
        List<String> resultList = board.allAroundFieldsOfBufferZoneIfAvailable(field);

        //Then
        Assertions.assertEquals(3,resultList.size());
        Assertions.assertTrue(resultList.contains("a2"));
        Assertions.assertTrue(resultList.contains("b2"));
        Assertions.assertTrue(resultList.contains("b1"));
    }
    @Test
    void testPreviousColumnIfAvailableWhenUnavailable() {
        //Given
        Board.setColumnsCount(2);
        Board.setRowsCount(2);
        Board board = new Board();
        String field = "a1";

        //When
        String result = board.previousColumnIfAvailable(field);

        //Then
        Assertions.assertEquals(null,result);
    }
    @Test
    void testPreviousColumnIfAvailableWhenAvailable() {
        //Given
        Board.setColumnsCount(2);
        Board.setRowsCount(2);
        Board board = new Board();
        String field = "b1";

        //When
        String result = board.previousColumnIfAvailable(field);

        //Then
        Assertions.assertEquals("a",result);
    }
    @Test
    void testNextColumnIfAvailableWhenUnavailable() {
        //Given
        Board.setColumnsCount(2);
        Board.setRowsCount(2);
        Board board = new Board();
        String field = "b1";

        //When
        String result = board.nextColumnIfAvailable(field);

        //Then
        Assertions.assertEquals(null,result);
    }
    @Test
    void testNextColumnIfAvailableWhenAvailable() {
        //Given
        Board.setColumnsCount(2);
        Board.setRowsCount(2);
        Board board = new Board();
        String field = "a1";

        //When
        String result = board.previousColumnIfAvailable(field);

        //Then
        Assertions.assertEquals("b",result);
    }
    @Test
    void testNextRowIfAvailableWhenUnavailable() {
        //Given
        Board.setColumnsCount(2);
        Board.setRowsCount(2);
        Board board = new Board();
        String field = "a2";

        //When
        String result = board.nextRowIfAvailable(field);

        //Then
        Assertions.assertEquals(null,result);
    }
    @Test
    void testNextRowIfAvailableWhenAvailable() {
        //Given
        Board.setColumnsCount(2);
        Board.setRowsCount(2);
        Board board = new Board();
        String field = "a1";

        //When
        String result = board.nextRowIfAvailable(field);

        //Then
        Assertions.assertEquals("2",result);
    }
    @Test
    void testPreviousRowIfAvailableWhenUnavailable() {
        //Given
        Board.setColumnsCount(2);
        Board.setRowsCount(2);
        Board board = new Board();
        String field = "a1";

        //When
        String result = board.previousRowIfAvailable(field);

        //Then
        Assertions.assertEquals(null,result);
    }
    @Test
    void testPreviousRowIfAvailableWhenAvailable() {
        //Given
        Board.setColumnsCount(2);
        Board.setRowsCount(2);
        Board board = new Board();
        String field = "a2";

        //When
        String result = board.previousRowIfAvailable(field);

        //Then
        Assertions.assertEquals("1",result);
    }
}
