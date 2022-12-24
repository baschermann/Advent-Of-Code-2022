package main.dayeight;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DayEightP1 {
    record Position(int x, int y) {}
    interface DirectionChange {
        Position moveToNewPosition(Position position);
    }

    private final Map<Position, Integer> trees = new HashMap<>();

    public DayEightP1(List<String> inputLines) {
        parseMap(inputLines);
        analyzeForest();
    }

    private void analyzeForest() {
        int hiddenTrees = 0;

        for (Position position : trees.keySet()) {
            if(isVisible(position, (p -> new Position(p.x, p.y - 1)))
                || isVisible(position, (p -> new Position(p.x, p.y + 1)))
                || isVisible(position, (p -> new Position(p.x - 1, p.y)))
                || isVisible(position, (p -> new Position(p.x + 1, p.y)))
            ) {
                hiddenTrees++;
            }
        }

        System.out.println("Trees hidden: " + hiddenTrees);
    }

    private boolean isVisible(Position position, DirectionChange directionChange) {
        Position currentPos = position;
        int referenceHeight = trees.get(position);
        Integer height;

        do {
            Position posToBeChecked = directionChange.moveToNewPosition(currentPos);
            height = trees.get(posToBeChecked);

            if(height != null) {
                currentPos = posToBeChecked;
                if(height >= referenceHeight) return false;
            }
        } while(height != null);

        return true;
    }

    private void parseMap(List<String> inputLines) {
        for(int x=0; x<inputLines.size(); x++) {
            char[] inputLine = inputLines.get(x).toCharArray();
            for(int y=0; y<inputLine.length; y++) {
                trees.put(new Position(x, y), Integer.parseInt(String.valueOf(inputLine[y])));
            }
        }
    }
}
