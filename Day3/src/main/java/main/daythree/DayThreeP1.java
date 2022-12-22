package main.daythree;

import java.io.IOException;
import java.util.List;

public class DayThreeP1 {

    public static String PRIORITY_STRING = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public DayThreeP1(List<String> inputLines) throws Exception {
        int totalPriorities = 0;

        for (String inputLine : inputLines) {
            String left = inputLine.substring(0, inputLine.length() / 2);
            String right = inputLine.substring(inputLine.length() / 2);

            totalPriorities += getPriorityOfSingleChar(left, right);
        }

        System.out.println("Total Priority points: " + totalPriorities);
    }

    private int getPriorityOfSingleChar(String left, String right) throws IOException {
        for (char charInLeft : left.toCharArray()) {
            if(right.contains(Character.toString(charInLeft))) {
                return PRIORITY_STRING.indexOf(charInLeft) + 1;
            }
        }

        throw new IOException();
    }
}
