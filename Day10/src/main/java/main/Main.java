package main;

import main.dayten.DayTenP1;
import main.dayten.DayTenP2;

import java.nio.file.Files;
import java.nio.file.Path;

public class Main {

    public static void main(String[] args) throws Exception {
        //new DayTenP1(Files.readAllLines(Path.of("input.txt")));
        new DayTenP2(Files.readAllLines(Path.of("input.txt")));
    }
}
