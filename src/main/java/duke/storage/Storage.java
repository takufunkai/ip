package duke.storage;

import duke.DukeException;
import duke.usertask.DukeSavable;
import duke.usertask.TaskList;

public interface Storage {
    void save(DukeSavable...tasks);
    void saveAndOverwrite(TaskList tasks);
    void remove(DukeSavable task);
    void update(DukeSavable updatedSave);
    void restore(TaskList emptyTasks) throws DukeException;
}
