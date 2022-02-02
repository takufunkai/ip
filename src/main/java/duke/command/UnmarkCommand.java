package duke.command;

import duke.DukeException;
import duke.usertask.TaskList;
import duke.usertask.UserTask;

/**
 * UnmarkCommand handles the marking of some task as not done. It receives a required argument, index, which is the
 * index of the task that the user wishes to mark as undone.
 */
public class UnmarkCommand extends Command {
    private final int index;

    /**
     * Creates an UnmarkCommand object with the specified index argument.
     *
     * @param index The index of the task that the user wishes to mark as not done.
     */
    public UnmarkCommand(int index) {
        this.index = index;
    }

    /**
     * Marks the task from the list of tasks given by <code>Duke</code> as undone. If the index does not yet exist,
     * <code>execute</code> will return an error, stating that the index does not exist.
     *
     * @param taskList The <code>TaskList</code> of the current user.
     * @throws DukeException Thrown if the index does not exist, i.e. it exceeds the current size of the TaskList.
     * @return
     */
    @Override
    public String execute(TaskList taskList) throws DukeException {
        if (index > taskList.getTasksCount()) {
            throw new DukeException("The task you are attempting to unmark does not exist");
        }
        UserTask task = taskList.unmarkTask(index);
        return "I thought you were done with it?\n" + task + "\n";
    }
}
