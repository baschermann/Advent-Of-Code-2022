package main;

import main.dayeleven.DayElevenP1;

import java.nio.file.Files;
import java.nio.file.Path;

public class Main {

    public static void main(String[] args) throws Exception {
        new DayElevenP1(Files.readAllLines(Path.of("input.txt")));
        //new DayElevenP2(Files.readAllLines(Path.of("input.txt")));
    }
}
