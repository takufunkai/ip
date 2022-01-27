package duke.command;

import duke.DukeException;
import duke.textui.TextUi;
import duke.usertask.Deadline;
import duke.usertask.TaskList;
import duke.usertask.UserTask;
import duke.usertask.UserTaskException;

public class DeadlineCommand extends Command {
    private final String description;
    private final String deadlineDateTime;

    public DeadlineCommand(String description, String deadlineDateTime) {
        this.description = description;
        this.deadlineDateTime = deadlineDateTime;
    }

    @Override
    public void execute(TextUi ui, TaskList taskList) throws DukeException {
        try {
            UserTask task = new Deadline(description, deadlineDateTime);
            taskList.addTask(task);
            ui.printFromRed("Added task #" + (taskList.getTasksCount()) + ": " + task + "\n");
        } catch (UserTaskException e) {
            throw new DukeException("Failed to create new deadline item: " + e.getMessage());
        }

    }
}
