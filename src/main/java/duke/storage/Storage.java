package duke.storage;

import duke.usertask.DukeSavable;

public interface Storage {
    void save(DukeSavable...tasks);
}
