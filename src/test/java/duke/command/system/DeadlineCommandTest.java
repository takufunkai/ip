package duke.command.system;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.DukeException;
import duke.command.usertask.DeadlineCommand;
import duke.usertask.TaskList;
import duke.utils.DukeResponse;

public class DeadlineCommandTest {
    @Test
    void execute_dateTimeInput() throws DukeException {
        TasksStorageStub storageStub = TasksStorageStub.getStub();
        TaskList tasks = new TaskList(1);

        String description = "Test 1";
        String inputDate = "27-09-1999 02:00";

        String taskString = String.format("[D][ ] %s (by: Sep 27 1999 02:00 AM)", description);

        DukeResponse expected = new DukeResponse(
                DukeResponse.ResponseStatus.SUCCESS,
                "Added task #1: " + taskString + "\n"
        );
        DukeResponse actual = new DeadlineCommand(description, inputDate)
                .supply(storageStub, tasks)
                .execute();

        assertEquals(expected, actual);
    }

    @Test
    void execute_onlyDateInput() throws DukeException {
        TasksStorageStub storageStub = TasksStorageStub.getStub();
        TaskList tasks = new TaskList(1);

        String description = "Test 1";
        String inputDate = "27-09-1999";

        String taskString = String.format("[D][ ] %s (by: Sep 27 1999 12:00 AM)", description);

        DukeResponse expected = new DukeResponse(
                DukeResponse.ResponseStatus.SUCCESS,
                "Added task #1: " + taskString + "\n"
        );
        DukeResponse actual = new DeadlineCommand(description, inputDate)
                .supply(storageStub, tasks)
                .execute();

        assertEquals(expected, actual);
    }
}
