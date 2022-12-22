package main.dayfour;

import java.util.List;

public class DayFourP2 {

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

        public boolean isInRange(Range other) {
            return right >= other.left && left <= other.right;
        }
    }

    public DayFourP2(List<String> inputLines) {
        int totalOverlaps = 0;
        int overlaps = 0;

        for (String inputLine : inputLines) {
            String[] sections = inputLine.split(",");

            Range rangeOne = parseRange(sections[0]);
            Range rangeTwo = parseRange(sections[1]);

            if(rangeOne.isOverlappingCompletely(rangeTwo) || rangeTwo.isOverlappingCompletely(rangeOne))
                totalOverlaps++;

            if(rangeOne.isInRange(rangeTwo))
                overlaps++;
        }

        System.out.println("Total Overlaps: " + totalOverlaps);
        System.out.println("Overlaps: " + overlaps);
    }

    private Range parseRange(String section) {
        String[] ranges = section.split("-");
        return new Range(Integer.parseInt(ranges[0]), Integer.parseInt(ranges[1]));
    }
}
