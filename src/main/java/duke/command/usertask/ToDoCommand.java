package duke.command.usertask;

import duke.usertask.ToDo;
import duke.usertask.UserTask;
import duke.utils.DukeResponse;

/**
 * ToDoCommand handles the necessary arguments for the successful creation of a <code>ToDo</code> task object
 * and appends it to the existing task list. It receives 1 mandatory argument, <code>description</code>,
 * which is required in the creation of a ToDo task object.
 */
public class ToDoCommand extends UserTaskCommand {
    private final String description;

    /**
     * Creates a new ToDoCommand with the necessary, validated argument.
     *
     * @param description The description of the ToDo task.
     */
    public ToDoCommand(String description) {
        this.description = description;
    }

    /**
     * Creates a new ToDo task object, and adds it to the current task list being maintained by <code>Duke</code>.
     * Adds the item to the tasksStorage.
     * @return The response of the result of the execution.
     */
    @Override
    public DukeResponse execute() {
        UserTask task = new ToDo(description);
        this.tasks.addTask(task);
        tasksStorage.save(task);
        String responseMessage = "Added task #" + (this.tasks.getTasksCount()) + ": " + task + "\n";
        return new DukeResponse(DukeResponse.ResponseStatus.SUCCESS, responseMessage);
    }
}
