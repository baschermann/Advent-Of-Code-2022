package main;

import main.dayeight.DayEightP1;

import java.nio.file.Files;
import java.nio.file.Path;

public class Main {

    public static void main(String[] args) throws Exception {
        new DayEightP1(Files.readAllLines(Path.of("input.txt")));
        //new DayEightP2(Files.readAllLines(Path.of("input.txt")));
    }
}
