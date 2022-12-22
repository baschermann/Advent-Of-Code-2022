package main;

import main.daytwo.DayTwo;

import java.nio.file.Files;
import java.nio.file.Path;

public class Main {

    public static void main(String[] args) throws Exception {
        new DayTwo(Files.readAllLines(Path.of("input.txt")));
    }
}
