package duke.command;

import duke.textui.TextUi;
import duke.usertask.TaskList;
import duke.usertask.UserTask;

public class UnmarkCommand extends Command {
    private final int index;

    public UnmarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TextUi ui, TaskList taskList) {
        UserTask task = taskList.unmarkTask(index);
        ui.printFromRed("I thought you were done with it?");
        ui.printFromRed(task + "\n");
    }
}
