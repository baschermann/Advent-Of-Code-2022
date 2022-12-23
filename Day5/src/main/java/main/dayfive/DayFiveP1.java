package main.dayfive;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class DayFiveP1 {

    private LinkedList<LinkedList<String>> stacks = new LinkedList<>();

    public DayFiveP1(List<String> inputLines) {
        parseInput(inputLines);

        printTopItemsOnStacks();
    }

    private void parseInput(List<String> inputLines) {
        for (String inputLine : inputLines) {
            if(inputLine.contains("[")) parseLineStack(inputLine);
            if(inputLine.contains("move")) parseAndMove(inputLine);
        }
    }

    private void parseAndMove(String inputLine) {
        String[] inputs = inputLine.split(" ");

        int numberOfMoves = Integer.parseInt(inputs[1]);
        int from = Integer.parseInt(inputs[3]);
        int to = Integer.parseInt(inputs[5]);

        for(int i = 0; i < numberOfMoves; i++) {
            move(from - 1, to - 1);
            printTopItemsOnStacks();
        }
    }

    private void move(int from, int to) {
        String item = stacks.get(from).removeLast();
        stacks.get(to).addLast(item);
    }

    private void parseLineStack(String inputLine) {
        String leftover= inputLine;
        int column = 0;
        while (leftover.length() >= 3) {
            String stackItemString = leftover.substring(0, 3).replace("[", "").replace("]", "").strip();

            if(stacks.size() <= column) stacks.add(new LinkedList<>());
            if(!stackItemString.isEmpty()) stacks.get(column).push(stackItemString);

            leftover = leftover.substring(Math.min(4, leftover.length()));
            column++;
        }
    }

    private void printTopItemsOnStacks() {
        //System.out.println("Number of Stacks: " + stacks.size());
        System.out.print("Top items on Stack: ");
        for (LinkedList<String> stack : stacks) {
            if(stack.size() > 0) {
                System.out.print(stack.getLast());
            } else {
                System.out.print(" ");
            }
        }
        System.out.println();
    }
}
