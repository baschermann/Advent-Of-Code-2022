package main.dayseven.part2;

import java.util.List;

public class DaySevenP2 {

    private final static long TOTAL_SPACE = 70000000;
    private final static long AT_LEAST_REQUIRED = 30000000;

    private Directory root = new Directory("/", null);
    private Directory currentDir = root;
    private Directory directoryToDelete = currentDir;

    public DaySevenP2(List<String> inputLines) {

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
        checkAllFolders(root);

        System.out.println("Directory size delete: " + directoryToDelete.getSize());
    }

    private void checkAllFolders(Directory directoryToCrawl) {
        for (SystemEntry child : directoryToCrawl.getChildren()) {
            if(child instanceof Directory) {
                Directory directory = (Directory) child;

                if((TOTAL_SPACE - root.getSize()) + directory.getSize() >= AT_LEAST_REQUIRED)
                    if(directory.getSize() < directoryToDelete.getSize())
                        directoryToDelete = directory;

                 checkAllFolders(directory);
            }
        }
    }
}
