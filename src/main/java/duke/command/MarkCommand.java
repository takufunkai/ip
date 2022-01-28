package duke.command;

import duke.DukeException;
import duke.textui.TextUi;
import duke.usertask.TaskList;
import duke.usertask.UserTask;

/**
 * MarkCommand handles the marking of some task as done. It receives a required argument, index, which is the
 * index of the task that the user wishes to mark as done.
 */
public class MarkCommand extends Command {
    private final int index;

    /**
     * Creates a MarkCommand object with the specified index argument.
     *
     * @param index The index of the task that the user wishes to mark as done.
     */
    public MarkCommand(int index) {
        this.index = index;
    }

    /**
     * Marks the task from the list of tasks given by <code>Duke</code> as done. If the index does not yet exist,
     * <code>execute</code> will return an error, stating that the index does not exist.
     *
     * @param ui       The <code>TextUi</code> object being used by <code>Duke</code>.
     * @param taskList The <code>TaskList</code> of the current user.
     * @throws DukeException Thrown if the index does not exist, i.e. it exceeds the current size of the TaskList.
     */
    @Override
    public void execute(TextUi ui, TaskList taskList) throws DukeException {
        if (index > taskList.getTasksCount()) {
            throw new DukeException("The task you are attempting to mark does not exist");
        }
        UserTask task = taskList.markTask(index);
        ui.printFromRed("Good job! Let's keep it going, this spaceship needs you!\n");
        ui.printFromRed(task + "\n");
    }
}
