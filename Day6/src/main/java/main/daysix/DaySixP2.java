package main.daysix;

import java.util.List;

public class DaySixP2 {

    private static int NUMBER_OF_UNIQUE_CHARS = 14;

    StringBuffer bufferUnique = new StringBuffer();
    int currentPos = 0;

    public DaySixP2(List<String> inputLines) {
        StringBuilder bufferInput = new StringBuilder(inputLines.get(0));

        while(!bufferCharactersAreUnique()) {
            bufferUnique.append(bufferInput.charAt(0));
            bufferInput.deleteCharAt(0);

            if(bufferUnique.length() > NUMBER_OF_UNIQUE_CHARS) bufferUnique.deleteCharAt(0);
            currentPos++;
        }

        System.out.println("Starting sequence at pos " + currentPos + " is: " + bufferUnique.toString());
    }

    private boolean bufferCharactersAreUnique() {
        char[] sequence = bufferUnique.toString().toCharArray();

        if(sequence.length >= NUMBER_OF_UNIQUE_CHARS) {
            for (int firstCharIndex = 0; firstCharIndex < NUMBER_OF_UNIQUE_CHARS; firstCharIndex++) {
                for (int secondCharIndex = 0; secondCharIndex < NUMBER_OF_UNIQUE_CHARS; secondCharIndex++) {
                    if (firstCharIndex != secondCharIndex && sequence[firstCharIndex] == sequence[secondCharIndex])
                        return false;
                }
            }
            return true;
        } else {
            return false;
        }
    }
}
