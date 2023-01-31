package com.kodilla.seabattle.data;

import com.kodilla.seabattle.logic.Settings;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

public class ComputerPlayerTestSuite {

    @Test
    void testShipsSetup() {
        //Given
        Settings settings = new Settings();
        settings.setDefaultShipCountSettings(); // fourFieldShip * 1; threeFieldShip * 2; twoFieldShip * 3; oneFieldShip *4

        Board board = new Board();
        Board.setRowsCount(12);     // default
        Board.setColumnsCount(12);      // default

        Player computerPlayer = new ComputerPlayer();

        //When
        Map<Integer,Integer> shipCountSettingsMap = Settings.getShipCountSettings();
        int expectedNumberOfShips = 0;
        for (Map.Entry<Integer,Integer> entry : shipCountSettingsMap.entrySet()) {
            for (int i=0; i< entry.getValue(); i++) {
                expectedNumberOfShips++;
            }
        }
        System.out.println("CONTROL expected number of ships: " + expectedNumberOfShips);

        computerPlayer.shipsSetUp();
        List<Ship> computerPlayerShipsList = computerPlayer.getShips();
        int resultNumberOfShips = computerPlayerShipsList.size();

        //Then
        Assertions.assertEquals(expectedNumberOfShips,resultNumberOfShips);

    }
}
