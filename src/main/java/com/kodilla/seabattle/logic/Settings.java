package com.kodilla.seabattle.logic;

import java.util.*;

public class Settings extends Options {
    private final String choicesTitle = "Game Settings";
    private final List<String> settings = new ArrayList<>(Arrays.asList("ships configuration","players"));
    private static Map<Integer,Integer> shipCountSettings = new HashMap<>();
    private static boolean onePlayerGame = true;


    public void setShipCountSettings() {
        //temporarily - default values
        shipCountSettings.put(4,1);
        shipCountSettings.put(3,2);
        shipCountSettings.put(2,3);
        shipCountSettings.put(1,4);
    }

    @Override
    public List<String> getOptions() {
        return settings;
    }
    @Override
    public String getChoicesTitle() {
        return choicesTitle;
    }

    public static Map<Integer, Integer> getShipCountSettings() {
        return shipCountSettings;
    }

    public static void setShipCountSettings(Map<Integer, Integer> shipCountSettings) {
        Settings.shipCountSettings = shipCountSettings;
    }

    public static boolean isOnePlayerGame() {
        return onePlayerGame;
    }

    public static void setOnePlayerGame(boolean onePlayerGame) {
        Settings.onePlayerGame = onePlayerGame;
    }
}
