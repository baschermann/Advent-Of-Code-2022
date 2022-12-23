package main;

import main.dayseven.part1.DaySevenP1;
import main.dayseven.part2.DaySevenP2;

import java.nio.file.Files;
import java.nio.file.Path;

public class Main {

    public static void main(String[] args) throws Exception {
        //new DaySevenP1(Files.readAllLines(Path.of("input.txt")));
        new DaySevenP2(Files.readAllLines(Path.of("input.txt")));
    }
}
