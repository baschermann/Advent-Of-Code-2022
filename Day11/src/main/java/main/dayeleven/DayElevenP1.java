package main.dayeleven;

import java.util.*;

public class DayElevenP1 {

    interface Operation {
        int inspect(int item);
    }

    static final class Monkey {
        LinkedList<Integer> items;
        Operation operation;
        int divisor;
        int throwToIfFalse;
        int throwToIfTrue;
        int inspectingYourShit = 0;
    }

    private static final int NUMBER_OF_ROUNDS = 20;
    private final List<Monkey> monkeys = new ArrayList<>();

    public DayElevenP1(List<String> inputLines) {
        parseMonkeys(inputLines);

        for (int round = 0; round < NUMBER_OF_ROUNDS; round++) {
            playRound();
        }

        monkeys.sort(Comparator.comparingInt(m -> m.inspectingYourShit));
        Collections.reverse(monkeys);

        System.out.println("Monkey business value: " + monkeys.get(0).inspectingYourShit * monkeys.get(1).inspectingYourShit);
    }

    private void playRound() {
        for (Monkey monkey : monkeys) {
            while(monkey.items.size() > 0) {
                ++monkey.inspectingYourShit;
                Integer item = monkey.items.pop();
                item = monkey.operation.inspect(item);
                item = item / 3;
                monkeys.get(item % monkey.divisor == 0 ? monkey.throwToIfTrue : monkey.throwToIfFalse).items.add(item);
            }
        }
    }

    @SuppressWarnings("DuplicateExpressions")
    private void parseMonkeys(List<String> inputLines) {
        for (String in : inputLines) {
            in = in.trim();
            if(in.startsWith("Monkey")) monkeys.add(new Monkey());

            Monkey currentMonkey = monkeys.get(monkeys.size() - 1);
            if(in.startsWith("Starting items:")) parseStartingItems(currentMonkey, in);
            if(in.startsWith("Operation:")) parseOperation(currentMonkey, in);
            if(in.startsWith("Test:")) currentMonkey.divisor = Integer.parseInt(in.substring(in.lastIndexOf(" ") + 1));
            if(in.startsWith("If true:")) currentMonkey.throwToIfTrue = Integer.parseInt(in.substring(in.lastIndexOf(" ") + 1));
            if(in.startsWith("If false:")) currentMonkey.throwToIfFalse = Integer.parseInt(in.substring(in.lastIndexOf(" ") + 1));
        }
    }

    private void parseStartingItems(Monkey currentMonkey, String in) {
        currentMonkey.items = new LinkedList<>(Arrays.stream(in.split(": ")[1].split(", ")).map(Integer::parseInt).toList());
    }

    private void parseOperation(Monkey currentMonkey, String in) {
        String command = in.split(" = ")[1];
        String[] commandItems = command.split(" ");

        if("+".equals(commandItems[1])) {
            if("old".equals(commandItems[2])) {
                currentMonkey.operation = item -> item + item;
            } else {
                currentMonkey.operation = item -> item + Integer.parseInt(commandItems[2]);
            }
        }

        if("*".equals(commandItems[1])) {
            if("old".equals(commandItems[2])) {
                currentMonkey.operation = item -> item * item;
            } else {
                currentMonkey.operation = item -> item * Integer.parseInt(commandItems[2]);
            }
        }
    }
}
