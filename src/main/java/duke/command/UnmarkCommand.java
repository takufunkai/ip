package duke.command;

import duke.DukeException;
import duke.textui.TextUi;
import duke.usertask.TaskList;
import duke.usertask.UserTask;

public class UnmarkCommand extends Command {
    private final int index;

    public UnmarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TextUi ui, TaskList taskList) throws DukeException {
        if (index > taskList.getTasksCount()) {
            throw new DukeException("The task you are attempting to unmark does not exist");
        }
        UserTask task = taskList.unmarkTask(index);
        ui.printFromRed("I thought you were done with it?\n");
        ui.printFromRed(task + "\n");
    }
}
