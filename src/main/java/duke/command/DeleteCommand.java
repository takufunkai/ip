package duke.command;

import duke.textui.TextUi;
import duke.usertask.TaskList;
import duke.usertask.UserTask;

public class DeleteCommand extends Command {
    private final int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TextUi ui, TaskList taskList) {
        UserTask delTask = taskList.deleteTask(index);
        ui.printFromRed("Alright! Getting rid of the following task: ");
        ui.printFromRed(delTask + "\n");
    }
}
