package duke.command.system;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.DukeException;
import duke.command.usertask.ListCommand;
import duke.usertask.Deadline;
import duke.usertask.Event;
import duke.usertask.TaskList;
import duke.usertask.ToDo;
import duke.usertask.UserTaskException;
import duke.utils.DukeResponse;

public class ListCommandTest {
    @Test
    void execute() throws UserTaskException, DukeException {
        TasksStorageStub storageStub = TasksStorageStub.getStub();
        TaskList tasks = new TaskList(5);
        tasks.addTask(new ToDo("Test 1"));

        String inputDate = "27-09-1999";
        String inputDateTime = "27-09-1999 14:00";
        tasks.addTask(new Event("Test 2", inputDate));
        tasks.addTask(new Event("Test 3", inputDateTime));

        tasks.addTask(new Deadline("Test 4", inputDate));
        tasks.addTask(new Deadline("Test 5", inputDateTime));

        String[] expectedTaskOutput = {
            "[T][ ] Test 1",
            "[E][ ] Test 2 (at: Sep 27 1999 12:00 AM)",
            "[E][ ] Test 3 (at: Sep 27 1999 02:00 PM)",
            "[D][ ] Test 4 (by: Sep 27 1999 12:00 AM)",
            "[D][ ] Test 5 (by: Sep 27 1999 02:00 PM)"
        };

        StringBuilder expectedSb = new StringBuilder();
        expectedSb.append("Alright, here are your tasks.")
                .append("\n----------\nTOTAL: 5 tasks\n----------\n");
        for (int i = 0; i < 5; i++) {
            expectedSb.append(i + 1).append(". ").append(expectedTaskOutput[i]).append("\n");
        }

        DukeResponse expected = new DukeResponse(DukeResponse.ResponseStatus.SUCCESS, expectedSb.toString());
        DukeResponse actual = new ListCommand().supply(storageStub, tasks).execute();

        assertEquals(expected, actual);
    }
}
