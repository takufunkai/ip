package duke.command;

import duke.textui.TextUi;
import duke.usertask.TaskList;

/**
 * ByeCommand handles the user command "bye". The object should alert <code>Duke</code> that
 * the program is being terminated by the user.
 */
public class ByeCommand extends Command {
    /**
     * Does nothing, as it has no execution.
     *
     * @param ui       The <code>TextUi</code> object being used by <code>Duke</code>.
     * @param taskList The <code>TaskList</code> of the current user.
     */
    @Override
    public void execute(TextUi ui, TaskList taskList) {
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
