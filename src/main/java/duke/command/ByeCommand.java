package duke.command;

import duke.textui.TextUi;
import duke.usertask.TaskList;

public class ByeCommand extends Command {
    @Override
    public void execute(TextUi ui, TaskList taskList) {
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
