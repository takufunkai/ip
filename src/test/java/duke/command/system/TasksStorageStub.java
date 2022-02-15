package duke.command.system;

import duke.DukeException;
import duke.storage.Storage;
import duke.usertask.DukeSavable;
import duke.usertask.TaskList;

public class TasksStorageStub implements Storage {
    private static final TasksStorageStub STORAGE_STUB = new TasksStorageStub();

    private TasksStorageStub() {
    }

    public static TasksStorageStub getStub() {
        return TasksStorageStub.STORAGE_STUB;
    }

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
