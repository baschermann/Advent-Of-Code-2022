package main;

import main.dayfive.DayFiveP1;
import main.dayfive.DayFiveP2;

import java.nio.file.Files;
import java.nio.file.Path;

public class Main {

    public static void main(String[] args) throws Exception {
        //new DayFiveP1(Files.readAllLines(Path.of("input.txt")));
        new DayFiveP2(Files.readAllLines(Path.of("input.txt")));
    }
}
