package main.dayten;

import java.util.Arrays;
import java.util.List;

public class DayTenP1 {

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

    private Operation currentOperation = null;

    public DayTenP1(List<String> inputLines) {
        int currentLineNumber = 0;
        int sumOfSignalStrengths = 0;
        int valueX = 1;
        int currentTick = 0;

        do {
            currentTick++;

            if(currentOperation == null)
                parseCommand(inputLines.get(currentLineNumber++));

            if(currentOperation.numOfCycles == 0) {
                valueX = currentOperation.operationResult.operate(valueX);
                parseCommand(inputLines.get(currentLineNumber++));
            }

            --currentOperation.numOfCycles;

            if(getCyclesToObserve().contains(currentTick)) {
                sumOfSignalStrengths += currentTick * valueX;
            }
        } while(currentLineNumber < inputLines.size() && currentOperation != null);

        System.out.println("Sum of signalStrengths: " + sumOfSignalStrengths);
    }

    private List<Integer> getCyclesToObserve() {
        return Arrays.asList(20, 60, 100, 140, 180, 220);
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
