package main.daytwo;

import java.util.List;

public class DayTwo {

    public DayTwo(List<String> inputLines) {
        int totalScore = 0;

        for (String line : inputLines) {
            int laneScore = 0;

            String[] split = line.split(" ");

            String first = split[0];
            String second = split[1];


            switch (second) {
                case "X" -> { // Loose
                    switch (first) {
                        case "A" -> second = "Z";
                        case "B" -> second = "X";
                        case "C" -> second = "Y";
                    }
                }
                case "Y" -> { // Draw
                    switch (first) {
                        case "A" -> second = "X";
                        case "B" -> second = "Y";
                        case "C" -> second = "Z";
                    }
                }

                case "Z" -> { // Win
                    switch (first) {
                        case "A" -> second = "Y";
                        case "B" -> second = "Z";
                        case "C" -> second = "X";
                    }
                }
            }

            switch (second) {
                case "X" -> { // Rock
                    laneScore += 1;
                    switch (first) {
                        case "A" -> laneScore += 3;
                        case "C" -> laneScore += 6;
                    }
                }
                case "Y" -> { // Paper
                    laneScore += 2;
                    switch (first) {
                        case "B" -> laneScore += 3;
                        case "A" -> laneScore += 6;
                    }
                }
                case "Z" -> { // Scissor
                    laneScore += 3;
                    switch (first) {
                        case "C" -> laneScore += 3;
                        case "B" -> laneScore += 6;
                    }
                }
            }

            totalScore += laneScore;
        }

        System.out.println("Total Score: " + totalScore);
    }
}
