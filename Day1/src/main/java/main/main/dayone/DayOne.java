package main.main.dayone;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class DayOne {

    public DayOne() throws Exception {
        List<String> lines = Files.readAllLines(Path.of("input.txt"));

        List<Long> allCals = new ArrayList<>();
        long currentCals = 0;

        for (String line : lines) {
            try {
                currentCals += Long.parseLong(line);
            } catch (NumberFormatException e) {
                allCals.add(currentCals);
                currentCals = 0L;
            }
        }

        allCals.sort(Long::compareTo);

        System.out.println("Lowest cal: " + allCals.get(0));
        System.out.println("Highest cal: " + allCals.get(allCals.size() - 1));
        System.out.println("Top3: " + (allCals.get(allCals.size() - 1) + allCals.get(allCals.size() - 2) + allCals.get(allCals.size() - 3)));

    }
}
