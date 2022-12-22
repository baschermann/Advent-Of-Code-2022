package main.dayfour;

import java.util.List;

public class DayFourP1 {

    public static class Range {
        public int left;
        public int right;

        public Range(int left, int right) {
            this.left = left;
            this.right = right;
        }

        public boolean isOverlappingCompletely(Range other) {
            return other.left >= left && other.right <= right;
        }
    }

    public DayFourP1(List<String> inputLines) {
        int totalOverlaps = 0;

        for (String inputLine : inputLines) {
            String[] sections = inputLine.split(",");

            Range rangeOne = parseRange(sections[0]);
            Range rangeTwo = parseRange(sections[1]);

            if(rangeOne.isOverlappingCompletely(rangeTwo) || rangeTwo.isOverlappingCompletely(rangeOne))
                totalOverlaps++;
        }

        System.out.println("Total Overlaps: " + totalOverlaps);
    }

    private Range parseRange(String section) {
        String[] ranges = section.split("-");
        return new Range(Integer.parseInt(ranges[0]), Integer.parseInt(ranges[1]));
    }
}
