package main.daynine;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DayNineP2 {

    private static final int ROPE_LENGTH = 10;

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
    private final List<Position> ropeKnots = new ArrayList<>();

    public DayNineP2(List<String> inputLines) {
        for (int i = 0; i < 10; i++) {
            ropeKnots.add(new Position(0, 0));
        }
        visitedPositionsOfTail.add(new Position(0, 0));

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
             moveRope(directionMove.moveToPosition(ropeKnots.get(0)));
         }
    }

    private void moveRope(Position moveToPosition) {
        for (int i = 0; i < ropeKnots.size(); i++) {
            if(i == 0) {
                ropeKnots.set(0, moveToPosition);
            } else {
                ropeKnots.set(i, updateKnot(i));

                if(i == ropeKnots.size() - 1)
                    visitedPositionsOfTail.add(ropeKnots.get(i));
            }
        }
    }

    private Position updateKnot(int index) {
        Position posOfPreviousKnot = ropeKnots.get(index - 1);
        Position posToUpdate = ropeKnots.get(index);

        if(posToUpdate.isTouching(posOfPreviousKnot)) return posToUpdate;

        boolean moveDiagonally = !posToUpdate.isHorizontal(posOfPreviousKnot) && !posToUpdate.isVertical(posOfPreviousKnot);

        int x = 0;
        int y = 0;

        if(moveDiagonally || posToUpdate.isHorizontal(posOfPreviousKnot)) {
            int offset = posOfPreviousKnot.x - posToUpdate.x;
            if(offset < 0)  x = -1;
            if(offset > 0)  x = 1;
        }

        if(moveDiagonally || posToUpdate.isVertical(posOfPreviousKnot)) {
            int offset = posOfPreviousKnot.y - posToUpdate.y;
            if(offset < 0)  y = - 1;
            if(offset > 0)  y = 1;
        }

        return new Position(posToUpdate.x + x, posToUpdate.y + y);
    }
}