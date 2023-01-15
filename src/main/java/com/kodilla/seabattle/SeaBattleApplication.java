package com.kodilla.seabattle;

import com.kodilla.seabattle.logic.GameProcessor;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SeaBattleApplication {

    public static void main(String[] args) {

        GameProcessor processor = new GameProcessor();
        processor.processGame();
    }

}
