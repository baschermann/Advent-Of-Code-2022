package main.daysix;

import java.util.List;

public class DaySixP1 {

    StringBuffer bufferUnique = new StringBuffer();
    int currentPos = 0;

    public DaySixP1(List<String> inputLines) {
        StringBuilder bufferInput = new StringBuilder(inputLines.get(0));

        while(!bufferCharactersAreUnique()) {
            bufferUnique.append(bufferInput.charAt(0));
            bufferInput.deleteCharAt(0);

            if(bufferUnique.length() > 4) bufferUnique.deleteCharAt(0);
            currentPos++;
        }

        System.out.println("Starting sequence at pos " + currentPos + " is: " + bufferUnique.toString());
    }

    private boolean bufferCharactersAreUnique() {
        char[] sequence = bufferUnique.toString().toCharArray();

        if(sequence.length >= 4) {
            for (int firstCharIndex = 0; firstCharIndex < 4; firstCharIndex++) {
                for (int secondCharIndex = 0; secondCharIndex < 4; secondCharIndex++) {
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
