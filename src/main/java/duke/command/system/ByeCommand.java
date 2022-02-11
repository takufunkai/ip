package duke.command.system;

import duke.DukeException;

/**
 * ByeCommand handles the user command "bye". The object should alert <code>Duke</code> that
 * the program is being terminated by the user.
 */
public class ByeCommand extends SystemCommand {
    /**
     * Returns true to alert <code>Duke</code> to terminate itself.
     *
     * @return true
     */
    @Override
    public boolean isExit() {
        return true;
    }

    @Override
    public String execute() throws DukeException {
        return null;
    }
}
