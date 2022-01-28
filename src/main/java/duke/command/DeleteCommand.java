package duke.command;

import duke.DukeException;
import duke.textui.TextUi;
import duke.usertask.TaskList;
import duke.usertask.UserTask;

/**
 * DeleteCommand handles the deletion of the given task at the specified index. It contains the argument
 * <code>index</code>, which is the index of the task that the user wants to delete.
 * DeleteCommand will check if the target index exists before attempting to delete.
 */
public class DeleteCommand extends Command {
    private final int index;

    /**
     * Stores the necessary argument for the delete operation.
     *
     * @param index The index of the item that the user wants to delete.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Deletes the task from the list of tasks given by <code>Duke</code>. If the index does not yet exist,
     * <code>execute</code> will return an error, stating that the index does not exist.
     *
     * @param ui       The <code>TextUi</code> object being used by <code>Duke</code>.
     * @param taskList The <code>TaskList</code> of the current user.
     * @throws DukeException Thrown if the index does not exist, i.e. it exceeds the current size of the TaskList.
     */
    @Override
    public void execute(TextUi ui, TaskList taskList) throws DukeException {
        if (index > taskList.getTasksCount()) {
            throw new DukeException("The task you are attempting to delete does not exist");
        }
        UserTask delTask = taskList.deleteTask(index);
        ui.printFromRed("Alright! Getting rid of the following task: \n");
        ui.printFromRed(delTask + "\n");
    }
}
