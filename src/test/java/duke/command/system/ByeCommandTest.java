package duke.command.system;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.DukeException;
import duke.utils.DukeResponse;

/**
 * Tests the ByeCommand.
 */
public class ByeCommandTest {
    @Test
    void execute() throws DukeException {
        DukeResponse expected = new DukeResponse(DukeResponse.ResponseStatus.EXIT);
        DukeResponse actual = new ByeCommand().execute();

        assertEquals(expected, actual);
    }
}
