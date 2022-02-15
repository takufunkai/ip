package duke.command.usertask;

import duke.DukeException;
import duke.usertask.UserTask;
import duke.utils.DukeResponse;

/**
 * UnmarkCommand handles the marking of some task as not done. It receives a required argument, index, which is the
 * index of the task that the user wishes to mark as undone.
 */
public class UnmarkCommand extends UserTaskCommand {
    private final int index;

    /**
     * Creates an UnmarkCommand object with the specified index argument.
     *
     * @param index The index of the task that the user wishes to mark as not done.
     */
    protected UnmarkCommand(int index) {
        this.index = index;
    }

    /**
     * Marks the task from the list of tasks given by <code>Duke</code> as undone. If the index does not yet exist,
     * <code>execute</code> will return an error, stating that the index does not exist.
     *
     * @throws DukeException Thrown if the index does not exist, i.e. it exceeds the current size of the TaskList.
     * @return The response of the result of the execution.
     */
    @Override
    public DukeResponse execute() throws DukeException {
        if (index > this.tasks.getTasksCount()) {
            throw new DukeException("The task you are attempting to unmark does not exist");
        }
        UserTask task = this.tasks.unmarkTask(index);
        tasksStorage.update(task);
        String responseMessage = "I thought you were done with it?\n" + task + "\n";
        return new DukeResponse(DukeResponse.ResponseStatus.SUCCESS, responseMessage);
    }
}
