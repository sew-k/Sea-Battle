package com.kodilla.seabattle.data;

import com.kodilla.seabattle.presentation.Keyboard;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class HumanPlayer extends Player {

    private String name;
    private List<Ship> ships = new ArrayList<>();
    private Set<String> shots = new HashSet<>();

    @Override
    public List<Ship> getShips() {
        return ships;
    }
    @Override
    public void addShot(String shot) {
        this.shots.add(shot);
    }
    @Override
    public String selectTarget() {
        Keyboard keyboard = new Keyboard();
        String target = keyboard.getString();
        return target;
    }
    @Override
    public void setShips(List<Ship> ships) {
        this.ships = ships;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }


    @Override
    public String getName() {
        return name;
    }
    @Override
    public void addShip(Ship ship) {
        this.ships.add(ship);
    }
    @Override
    public Set<String> getShots() {
        return shots;
    }
}
