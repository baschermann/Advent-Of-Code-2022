package main.dayseven.part1;

import java.util.ArrayList;
import java.util.List;

public class File extends SystemEntry {

    private long size;

    public File(String name, Directory parent, long size) {
        super(name, parent);
        this.size = size;
    }

    @Override
    List<SystemEntry> getChildren() {
        return new ArrayList<>();
    }

    @Override
    SystemEntry getChild(String name) {
        return null;
    }

    @Override
    long getSize() {
        return size;
    }
}
