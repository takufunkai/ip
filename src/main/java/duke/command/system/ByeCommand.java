package duke.command.system;

import duke.DukeException;
import duke.utils.DukeResponse;

/**
 * ByeCommand handles the user command "bye". The object should alert <code>Duke</code> that
 * the program is being terminated by the user.
 */
public class ByeCommand extends SystemCommand {
    @Override
    public DukeResponse execute() throws DukeException {
        return new DukeResponse(DukeResponse.ResponseStatus.EXIT);
    }
}
