package duke.command;

import duke.storage.SaveHandler;
import duke.usertask.TaskList;
import duke.usertask.ToDo;
import duke.usertask.UserTask;

/**
 * ToDoCommand handles the necessary arguments for the successful creation of a <code>ToDo</code> task object
 * and appends it to the existing task list. It receives 1 mandatory argument, <code>description</code>,
 * which is required in the creation of a ToDo task object.
 */
public class ToDoCommand extends Command {
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
     * Adds the item to the saveHandler.
     *
     * @param taskList The <code>TaskList</code> of the current user.
     * @param saveHandler The SaveHandler used by Duke.
     */
    @Override
    public String execute(TaskList taskList, SaveHandler saveHandler) {
        UserTask task = new ToDo(description);
        taskList.addTask(task);
        saveHandler.save(task);
        return "Added task #" + (taskList.getTasksCount()) + ": " + task + "\n";
    }
}
