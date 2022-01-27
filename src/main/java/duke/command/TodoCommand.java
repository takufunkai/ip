package duke.command;

import duke.textui.TextUi;
import duke.usertask.TaskList;
import duke.usertask.ToDo;
import duke.usertask.UserTask;

public class TodoCommand extends Command {
    private final String description;

    public TodoCommand(String description) {
        this.description = description;
    }

    @Override
    public void execute(TextUi ui, TaskList taskList) {
        UserTask task = new ToDo(description);
        taskList.addTask(task);
        ui.printFromRed("Added task #" + (taskList.getTasksCount()) + ": " + task + "\n");
    }
}
