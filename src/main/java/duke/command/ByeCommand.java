package duke.command;

import duke.storage.SaveHandler;
import duke.usertask.TaskList;

/**
 * ByeCommand handles the user command "bye". The object should alert <code>Duke</code> that
 * the program is being terminated by the user.
 */
public class ByeCommand extends Command {
    /**
     * Does nothing, as it has no execution.
     *
     * @param taskList The <code>TaskList</code> of the current user.
     * @param saveHandler The SaveHandler used by Duke.
     */
    @Override
    public String execute(TaskList taskList, SaveHandler saveHandler) {
        return "";
    }

    /**
     * Returns true to alert <code>Duke</code> to terminate itself.
     *
     * @return true
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
