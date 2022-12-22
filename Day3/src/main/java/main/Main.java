package main;

import main.daythree.DayThreeP2;

import java.nio.file.Files;
import java.nio.file.Path;

public class Main {

    public static void main(String[] args) throws Exception {
        new DayThreeP2(Files.readAllLines(Path.of("input.txt")));
    }
}
