package main.dayten;

import java.util.Arrays;
import java.util.List;

public class DayTenP2 {

    interface OperationResult {
        int operate(int input);
    }

    static final class Operation {
        private int numOfCycles;
        private final OperationResult operationResult;

        Operation(int numOfCycles, OperationResult operationResult) {
            this.numOfCycles = numOfCycles;
            this.operationResult = operationResult;
        }
    }

    private static final int DRAW_LINE_WIDTH = 40;
    private Operation currentOperation = null;

    public DayTenP2(List<String> inputLines) {
        int currentLineNumber = 0;
        int valueX = 1;
        int currentTick = 0;

        do {
            currentTick++;

            if(currentOperation == null)
                parseCommand(inputLines.get(currentLineNumber++));

            --currentOperation.numOfCycles;
            if(currentOperation.numOfCycles == 0) {
                valueX = currentOperation.operationResult.operate(valueX);
                parseCommand(inputLines.get(currentLineNumber++));
            }

            drawOnCrt(currentTick, valueX);
        } while(currentLineNumber < inputLines.size());
    }

    private void drawOnCrt(int currentTick, int valueX) {
        if(currentTick % 40 == 0) System.out.println();

        if(Math.abs(valueX - currentTick % 40) <= 1)
            System.out.print("#");
        else
            System.out.print(".");
    }

    private void parseCommand(String inputLine) {
        if(inputLine.startsWith("noop")) {
            executeNoop();
         } else {
            if(inputLine.startsWith("addx")) {
                String[] values = inputLine.split(" ");
                executeAddx(Integer.parseInt(values[1]));
            }
        }
    }

    private void executeAddx(int valueToAdd) {
        currentOperation = new Operation(2, x -> x + valueToAdd);
    }

    private void executeNoop() {
        currentOperation = new Operation(1, x -> x);
    }
}
