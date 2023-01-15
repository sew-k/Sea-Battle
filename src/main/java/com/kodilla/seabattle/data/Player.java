package com.kodilla.seabattle.data;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Player {
    private String name;
    private List<Ship> ships;
    private int score;
    private Set<String> shots = new HashSet<>();

    public String getName() {
        return name;
    }

    public void addShot(String shot) {
        this.shots.add(shot);
    }

    public Set<String> getShots() {
        return shots;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Ship> getShips() {
        return ships;
    }

    public void setShips(List<Ship> ships) {
        this.ships = ships;
    }

    public String selectTarget() {
        //temporarily
        return null;
    }

    public void addShip(Ship ship) {
        this.ships.add(ship);
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
