package main.dayseven.part2;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Directory extends SystemEntry {

    private final List<SystemEntry> children = new ArrayList<>();

    public Directory(String name, Directory parent) {
        super(name, parent);
    }

    @Override
    List<SystemEntry> getChildren() {
        return children;
    }

    @Override
    SystemEntry getChild(String name) {
        return children.stream().filter(se -> se.name.equals(name)).findFirst().orElse(null);
    }

    public void addChildren(SystemEntry child) {
        children.add(child);
    }

    @Override
    long getSize() {
        return children.stream().collect(Collectors.summarizingLong(SystemEntry::getSize)).getSum();
    }
}
