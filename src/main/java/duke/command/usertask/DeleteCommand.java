package duke.command.usertask;

import duke.DukeException;
import duke.usertask.UserTask;
import duke.utils.DukeResponse;

/**
 * DeleteCommand handles the deletion of the given task at the specified index. It contains the argument
 * <code>index</code>, which is the index of the task that the user wants to delete.
 * DeleteCommand will check if the target index exists before attempting to delete.
 */
public class DeleteCommand extends UserTaskCommand {
    private final int index;

    /**
     * Stores the necessary argument for the delete operation.
     *
     * @param index The index of the item that the user wants to delete.
     */
    protected DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Deletes the task from the list of tasks given by <code>Duke</code>. If the index does not yet exist,
     * <code>execute</code> will return an error, stating that the index does not exist.
     *
     * Removes the item from the list of saved items.
     *
     * @throws DukeException Thrown if the index does not exist, i.e. it exceeds the current size of the TaskList.
     * @return The response of the result of the execution.
     */
    @Override
    public DukeResponse execute() throws DukeException {
        if (index > super.tasks.getTasksCount()) {
            throw new DukeException("The task you are attempting to delete does not exist");
        }
        UserTask delTask = super.tasks.deleteTask(index);
        tasksStorage.remove(delTask);
        String responseMessage = "Alright! Getting rid of the following task: \n" + delTask + "\n";
        return new DukeResponse(DukeResponse.ResponseStatus.SUCCESS, responseMessage);
    }
}
