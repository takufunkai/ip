package duke.command;

import duke.DukeException;
import duke.textui.TextUi;
import duke.usertask.TaskList;
import duke.usertask.UserTask;

public class MarkCommand extends Command {
    private final int index;

    public MarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TextUi ui, TaskList taskList) throws DukeException {
        if (index > taskList.getTasksCount()) {
            throw new DukeException("The task you are attempting to mark does not exist");
        }
        UserTask task = taskList.markTask(index);
        ui.printFromRed("Good job! Let's keep it going, this spaceship needs you!\n");
        ui.printFromRed(task + "\n");
    }
}
