package duke.command;

import duke.DukeException;
import duke.usertask.Deadline;
import duke.usertask.TaskList;
import duke.usertask.UserTask;
import duke.usertask.UserTaskException;

/**
 * DeadlineCommand handles the necessary arguments for the successful creation of a <code>Deadline</code> task object
 * and appends it to the existing task list. It receives 2 mandatory arguments, <code>description</code> and
 * <code>deadlineDateTime</code>, which are required in the creation of a Deadline task object.
 */
public class DeadlineCommand extends Command {
    private final String description;
    private final String deadlineDateTime;

    /**
     * Creates a new DeadlineCommand with the necessary, validated arguments.
     *
     * @param description      The description of the Deadline task.
     * @param deadlineDateTime The due-date for the Deadline task.
     */
    public DeadlineCommand(String description, String deadlineDateTime) {
        this.description = description;
        this.deadlineDateTime = deadlineDateTime;
    }

    /**
     * Creates a new Deadline task object, and adds it to the current task list being maintained by <code>Duke</code>.
     *
     * @param taskList The <code>TaskList</code> of the current user.
     * @throws DukeException Thrown if Deadline object was unsuccessfully created.
     * @return
     */
    @Override
    public String execute(TaskList taskList) throws DukeException {
        try {
            UserTask task = new Deadline(description, deadlineDateTime);
            taskList.addTask(task);
            return "Added task #" + (taskList.getTasksCount()) + ": " + task + "\n";
        } catch (UserTaskException e) {
            throw new DukeException("Failed to create new deadline item: " + e.getMessage());
        }

    }
}
