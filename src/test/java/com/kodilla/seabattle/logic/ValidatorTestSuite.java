package com.kodilla.seabattle.logic;

import com.kodilla.seabattle.data.Board;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class ValidatorTestSuite {

    @Test
    void testValidateForOptionsIfCorrect() {
        //Given
        Menu menu = new Menu();
        Validator validator = new Validator();
        List<String> menuOptions = menu.getOptions();

        //When
        boolean result = true;
        for (String option : menuOptions) {
            String key = Integer.toString(menuOptions.indexOf(option));
            boolean validation = validator.validateForOptions(key,menu);
            if (!validation) {
                result = false;
            }
        }

        //Then
        Assertions.assertTrue(result);
    }
    @Test
    void testValidateForOptionsIfOutOfBoundsHigh() {
        //Given
        Menu menu = new Menu();
        Validator validator = new Validator();
        List<String> menuOptions = menu.getOptions();

        //When
        boolean result = true;
        String key = Integer.toString(menuOptions.size());
        result = validator.validateForOptions(key,menu);

        //Then
        Assertions.assertFalse(result);
    }
    @Test
    void testValidateForOptionsIfOutOfBoundsLow() {
        //Given
        Menu menu = new Menu();
        Validator validator = new Validator();
        List<String> menuOptions = menu.getOptions();

        //When
        boolean result = true;
        String key = Integer.toString(-1);
        result = validator.validateForOptions(key,menu);

        //Then
        Assertions.assertFalse(result);
    }
    @Test
    void testValidateForOptionsIfNotInteger() {
        //Given
        Menu menu = new Menu();
        Validator validator = new Validator();
        List<String> menuOptions = menu.getOptions();

        //When
        boolean result = true;
        String key = "a";
        result = validator.validateForOptions(key,menu);

        //Then
        Assertions.assertFalse(result);
    }

    @Test
    void testValidateForChangeSettingsIfYes() {
        //Given
        Settings settings = new Settings();
        Validator validator = new Validator();

        //When
        boolean result = false;
        String key = settings.getKeyForChangeSettings();
        result = validator.validateForChangeSettings(key,settings);

        //Then
        Assertions.assertTrue(result);
    }

    @Test
    void testValidateForChangeSettingsIfNo() {
        //Given
        Settings settings = new Settings();
        Validator validator = new Validator();

        //When
        boolean result = false;
        String key = settings.getKeyForLeaveSettings();
        result = validator.validateForChangeSettings(key,settings);

        //Then
        Assertions.assertTrue(result);
    }
    @Test
    void testValidateForChangeSettingsIncorrectKey() {
        //Given
        Settings settings = new Settings();
        Validator validator = new Validator();

        //When
        boolean result = false;
        String key = "aaa";
        result = validator.validateForChangeSettings(key,settings);

        //Then
        Assertions.assertFalse(result);
    }

    @Test
    void testValidateIsTargetOnBoardIfTargetOnBoard() {
        //Given
        Board board = new Board();
        Board.setRowsCount(5);
        Board.setColumnsCount(5);
        Validator validator = new Validator();

        //When
        String target = "a1";
        boolean result = false;
        result = validator.validateIsTargetOnBoard(target, board);

        //Then
        Assertions.assertTrue(result);
    }
    @Test
    void testValidateIsTargetOnBoardIfTargetOutOfBoard() {
        //Given
        Board board = new Board();
        Board.setRowsCount(5);
        Board.setColumnsCount(5);
        Validator validator = new Validator();

        //When
        String target = "z10";
        boolean result = true;
        result = validator.validateIsTargetOnBoard(target, board);

        //Then
        Assertions.assertFalse(result);
    }
}
