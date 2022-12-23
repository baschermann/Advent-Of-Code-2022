package main.dayseven.part1;

import java.util.List;

public class DaySevenP1 {

    private final static long DIRECTORY_SIZE_AT_MOST = 100000;

    private Directory root = new Directory("/", null);
    private Directory currentDir = root;

    public DaySevenP1(List<String> inputLines) {

        for (String inputLine : inputLines) {
            switch (parseLine(inputLine)) {
                case CD -> parseCD(inputLine);
                case DIR -> parseDir(inputLine);
                case FILE -> parseFile(inputLine);
            }
        }
        
        calculatePuzzleOutput();
    }

    private void parseFile(String inputLine) {
        String[] inputs = inputLine.split(" ");

        String name = inputs[1];
        long size = Long.parseLong(inputs[0]);

        currentDir.addChildren(new File(name, currentDir, size));
    }

    private void parseDir(String inputLine) {
        String command = inputLine.substring(4);
        currentDir.addChildren(new Directory(command, currentDir));
    }

    private void parseCD(String inputLine) {
        String command = inputLine.substring(5);

        if(command.equals("..")) {
            currentDir = currentDir.getParent();
        } else {
            if(command.equals("/")) {
                currentDir = root;
            } else {
                currentDir = (Directory) currentDir.getChild(command);
            }
        }
    }

    private CommandType parseLine(String inputLine) {
        if(inputLine.startsWith("$ cd")) return CommandType.CD;
        if(inputLine.startsWith("$ ls")) return CommandType.LS;
        if(inputLine.startsWith("dir")) return CommandType.DIR;
        return CommandType.FILE;
    }

    private void calculatePuzzleOutput() {
        long totalSize = 0;
        System.out.println(checkAllFolders(root, totalSize));
    }

    private long checkAllFolders(Directory directoryToCheck, long totalSize) {
        for (SystemEntry child : directoryToCheck.getChildren()) {
            if(child instanceof Directory) {
                Directory directory = (Directory) child;

                if(directory.getSize() <= 100000)
                    totalSize += directory.getSize();

                totalSize = checkAllFolders(directory, totalSize);
            }
        }

        return totalSize;
    }
}
