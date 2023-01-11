package com.kodilla.seabattle;

import com.kodilla.seabattle.logic.GameProcessor;
import com.kodilla.seabattle.logic.Menu;
import com.kodilla.seabattle.presentation.Drawer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SeaBattleApplication {

    public static void main(String[] args) {

        GameProcessor processor = new GameProcessor();
        processor.showGameMenu();
    }

}
