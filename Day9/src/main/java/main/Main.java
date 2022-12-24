package main;

import main.daynine.DayNineP1;
import main.daynine.DayNineP2;

import java.nio.file.Files;
import java.nio.file.Path;

public class Main {

    public static void main(String[] args) throws Exception {
        //new DayNineP1(Files.readAllLines(Path.of("input.txt")));
        new DayNineP2(Files.readAllLines(Path.of("input.txt")));
    }
}
