package duke.command;

import duke.DukeException;
import duke.textui.TextUi;
import duke.usertask.Event;
import duke.usertask.TaskList;
import duke.usertask.UserTask;
import duke.usertask.UserTaskException;

public class EventCommand extends Command {
    private final String description;
    private final String eventDateTime;

    public EventCommand(String description, String eventDateTime) {
        this.description = description;
        this.eventDateTime = eventDateTime;
    }

    @Override
    public void execute(TextUi ui, TaskList taskList) throws DukeException {
        try {
            UserTask task = new Event(description, eventDateTime);
            taskList.addTask(task);
            ui.printFromRed("Added task #" + (taskList.getTasksCount()) + ": " + task + "\n");
        } catch (UserTaskException e) {
            throw new DukeException("Failed to create new event item: " + e.getMessage());
        }
    }
}
