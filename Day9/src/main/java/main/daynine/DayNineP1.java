package main.daynine;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DayNineP1 {

    record Position(int x, int y) {
        boolean isTouching(Position other) {
            double distance = Math.sqrt((other.y - y) * (other.y - y) + (other.x - x) * (other.x - x));
            return distance < 1.5;
        }

        boolean isHorizontal(Position other) {
            return y == other.y;
        }

        boolean isVertical(Position other) {
            return x == other.x;
        }
    }

    interface DirectionMove {
        Position moveToPosition(Position currentPos);
    }
    private final Set<Position> visitedPositionsOfTail = new HashSet<>();
    private Position headPos = new Position(0, 0);
    private Position tailPos = new Position(0, 0);

    public DayNineP1(List<String> inputLines) {
        visitedPositionsOfTail.add(tailPos);

        for (String inputLine : inputLines) {
            parseCommand(inputLine);
        }

        System.out.println("Number of visited positions: " + visitedPositionsOfTail.size());
    }

    private void parseCommand(String inputLine) {
        String[] split = inputLine.split(" ");

        String direction = split[0];
        int numberOfMoves = Integer.parseInt(split[1]);

        switch (direction) {
            case "R" -> moveNumberOfSteps(numberOfMoves, (p) -> new Position(p.x+1, p.y));
            case "L" -> moveNumberOfSteps(numberOfMoves, (p) -> new Position(p.x-1, p.y));
            case "U" -> moveNumberOfSteps(numberOfMoves, (p) -> new Position(p.x, p.y-1));
            case "D" -> moveNumberOfSteps(numberOfMoves, (p) -> new Position(p.x, p.y+1));
        }
    }

    private void moveNumberOfSteps(int numberOfSteps, DirectionMove directionMove) {
         for(int step = 0; step < numberOfSteps; step++) {
             moveHead(directionMove.moveToPosition(headPos));
         }
    }

    private void moveHead(Position moveToPosition) {
        Position oldHeadPos = headPos;
        headPos = moveToPosition;

        updateTailPos(oldHeadPos);
    }

    private void updateTailPos(Position oldHeadPos) {
        if(tailPos.isTouching(headPos)) return;

        if(!tailPos.isHorizontal(headPos) && !tailPos.isVertical(headPos)) {
            tailPos = oldHeadPos;
            visitedPositionsOfTail.add(tailPos);
            return;
        }

        if(tailPos.isHorizontal(headPos)) {
            int offset = headPos.x - tailPos.x;
            if(offset < 0) tailPos = new Position(tailPos.x - 1, tailPos.y);
            if(offset > 0) tailPos = new Position(tailPos.x + 1, tailPos.y);
        }

        if(tailPos.isVertical(headPos)) {
            int offset = headPos.y - tailPos.y;
            if(offset < 0) tailPos = new Position(tailPos.x, tailPos.y - 1);
            if(offset > 0) tailPos = new Position(tailPos.x, tailPos.y + 1);
        }

        visitedPositionsOfTail.add(tailPos);
    }
}