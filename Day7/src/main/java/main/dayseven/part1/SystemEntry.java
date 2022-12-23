package main.dayseven.part1;

import java.util.List;

public abstract class SystemEntry {

    protected Directory parent;
    String name;

    public SystemEntry(String name, Directory parent) {
        this.name = name;
        this.parent = parent;
    }

    Directory getParent() {
        return parent;
    }

    abstract List<SystemEntry> getChildren();

    abstract SystemEntry getChild(String name);

    abstract long getSize();
}
