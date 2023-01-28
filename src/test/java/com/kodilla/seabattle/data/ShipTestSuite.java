package com.kodilla.seabattle.data;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class ShipTestSuite {

    @Test
    void testCheckIfShipSinkWhenOnlyOneHit() {
        //Given
        Ship ship = new Ship();
        Map<String,String> shipStatus = new HashMap<>();
        shipStatus.put("a1","good");
        shipStatus.put("a2","good");
        ship.setStatusOnBoard(shipStatus);

        //When
        shipStatus.replace("a1","hit");
        ship.checkIfShipSink();

        //Then
        Assertions.assertEquals("good",ship.getStatusOnBoard().get("a2"));
        Assertions.assertEquals("hit",ship.getStatusOnBoard().get("a1"));
    }
    @Test
    void testCheckIfShipSinkWhenAllFieldsHit() {
        //Given
        Ship ship = new Ship();
        Map<String,String> shipStatus = new HashMap<>();
        shipStatus.put("a1","good");
        shipStatus.put("a2","good");
        ship.setStatusOnBoard(shipStatus);

        //When
        shipStatus.replace("a1","hit");
        shipStatus.replace("a2","hit");
        ship.checkIfShipSink();

        //Then
        Assertions.assertEquals("sink",ship.getStatusOnBoard().get("a2"));
        Assertions.assertEquals("sink",ship.getStatusOnBoard().get("a1"));
    }

    @Test
    void testSetBufferZone() {
        //Given
        Ship ship = new Ship();
        Map<String,String> shipStatus = new HashMap<>();
        shipStatus.put("a1","good");
        shipStatus.put("a2","good");
        ship.setStatusOnBoard(shipStatus);

        //When
        ship.setBufferZone();

        //Then
        Assertions.assertTrue(ship.getBufferZone().contains("b1"));
        Assertions.assertTrue(ship.getBufferZone().contains("b2"));
        Assertions.assertTrue(ship.getBufferZone().contains("b3"));
        Assertions.assertTrue(ship.getBufferZone().contains("a3"));
        Assertions.assertEquals(4,ship.getBufferZone().size());
    }
}
