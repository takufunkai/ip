package duke.command;

import duke.DukeException;
import duke.usertask.Event;
import duke.usertask.TaskList;
import duke.usertask.UserTask;
import duke.usertask.UserTaskException;

/**
 * EventCommand handles the necessary arguments for the successful creation of an <code>Event</code> task object
 * and appends it to the existing task list. It receives 2 mandatory arguments, <code>description</code> and
 * <code>eventDateTime</code>, which are required in the creation of an Event task object.
 */
public class EventCommand extends Command {
    private final String description;
    private final String eventDateTime;

    /**
     * Creates a new EventCommand with the necessary, validated arguments.
     *
     * @param description   The description of the Event.
     * @param eventDateTime The occurrence-date for the Event.
     */
    public EventCommand(String description, String eventDateTime) {
        this.description = description;
        this.eventDateTime = eventDateTime;
    }

    /**
     * Creates a new Event task object, and adds it to the current task list being maintained by <code>Duke</code>.
     *
     * @param taskList The <code>TaskList</code> of the current user.
     * @throws DukeException Thrown if Event object was unsuccessfully created.
     * @return
     */
    @Override
    public String execute(TaskList taskList) throws DukeException {
        try {
            UserTask task = new Event(description, eventDateTime);
            taskList.addTask(task);
            return "Added task #" + (taskList.getTasksCount()) + ": " + task + "\n";
        } catch (UserTaskException e) {
            throw new DukeException("Failed to create new event item: " + e.getMessage());
        }
    }
}
