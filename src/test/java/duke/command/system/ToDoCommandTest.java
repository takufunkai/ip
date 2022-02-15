package duke.command.system;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.DukeException;
import duke.command.usertask.ToDoCommand;
import duke.usertask.TaskList;
import duke.utils.DukeResponse;

public class ToDoCommandTest {
    @Test
    void execute() throws DukeException {
        TasksStorageStub storageStub = TasksStorageStub.getStub();
        TaskList tasks = new TaskList(1);

        String description = "Test 1";
        String taskString = "[T][ ] " + description;
        DukeResponse expected = new DukeResponse(
                DukeResponse.ResponseStatus.SUCCESS, "Added task #1: " + taskString + "\n"
        );
        DukeResponse actual = new ToDoCommand(description)
                .supply(storageStub, tasks)
                .execute();

        assertEquals(expected, actual);
    }
}
