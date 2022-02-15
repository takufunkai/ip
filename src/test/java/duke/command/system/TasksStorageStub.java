package duke.command.system;

import duke.DukeException;
import duke.storage.Storage;
import duke.usertask.DukeSavable;
import duke.usertask.TaskList;

public class TasksStorageStub implements Storage {
    @Override
    public void save(DukeSavable... tasks) {
    }
    @Override
    public void saveAndOverwrite(TaskList tasks) {
    }
    @Override
    public void remove(DukeSavable task) {
    }
    @Override
    public void update(DukeSavable updatedSave) {
    }
    @Override
    public void restore(TaskList emptyTasks) throws DukeException {
    }
}
