package main.dayeight;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DayEightP2 {
    record Position(int x, int y) {}
    interface DirectionChange {
        DayEightP2.Position moveToNewPosition(DayEightP2.Position position);
    }

    private final Map<Position, Integer> trees = new HashMap<>();

    public DayEightP2(List<String> inputLines) {
        parseMap(inputLines);
        analyzeForest();
    }

    private void analyzeForest() {
        int highestScenicView = 0;

        for (Position position : trees.keySet()) {
            int currentScenicView = 1;
            currentScenicView *= amountOfTreesUntilBlocked(position, (p -> new Position(p.x, p.y-1)));
            currentScenicView *= amountOfTreesUntilBlocked(position, (p -> new Position(p.x, p.y+1)));
            currentScenicView *= amountOfTreesUntilBlocked(position, (p -> new Position(p.x-1, p.y)));
            currentScenicView *= amountOfTreesUntilBlocked(position, (p -> new Position(p.x+1, p.y)));

            highestScenicView = Math.max(currentScenicView, highestScenicView);
        }

        System.out.println("Highest scenic view: " + highestScenicView);
    }

    private int amountOfTreesUntilBlocked(Position position, DirectionChange directionChange) {
        Position currentPos = position;
        int referenceHeight = trees.get(position);
        Integer height;

        int numOfTrees = 0;

        do {
            Position posToBeChecked = directionChange.moveToNewPosition(currentPos);
            height = trees.get(posToBeChecked);

            if(height != null) {
                numOfTrees++;
                currentPos = posToBeChecked;
            }
        } while(height != null && height < referenceHeight);

        return numOfTrees;
    }

    private void parseMap(List<String> inputLines) {
        for(int y=0; y<inputLines.size(); y++) {
            char[] inputLine = inputLines.get(y).toCharArray();
            for(int x=0; x<inputLine.length; x++) {
                trees.put(new Position(x, y), Integer.parseInt(String.valueOf(inputLine[x])));
            }
        }
    }
}
