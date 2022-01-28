package duke.command;

import duke.DukeException;
import duke.textui.TextUi;
import duke.usertask.TaskList;
import duke.usertask.UserTask;

public class DeleteCommand extends Command {
    private final int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

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
