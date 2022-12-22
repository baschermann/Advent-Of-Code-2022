package main.daythree;

import java.io.IOException;
import java.util.List;

public class DayThreeP2 {

    public static String PRIORITY_STRING = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public DayThreeP2(List<String> inputLines) throws Exception {
        int totalPriorities = 0;

        for (int group = 0; group < inputLines.size(); group += 3) {
            char uniqueChar = getUniqueChar(inputLines.get(group), inputLines.get(group + 1), inputLines.get(group + 2));
            totalPriorities += PRIORITY_STRING.indexOf(uniqueChar) + 1;
        }

        System.out.println("Total Priority points: " + totalPriorities);
    }

    private char getUniqueChar(String first, String second, String third) throws IOException {
        for (char charInLeft : first.toCharArray()) {
            if(second.contains(Character.toString(charInLeft)) &&
                    third.contains(Character.toString(charInLeft))) {
                return charInLeft;
            }
        }

        throw new IOException();
    }
}
