package duke.command;

import duke.DukeException;
import duke.textui.TextUi;
import duke.usertask.TaskList;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

public class ListCommand extends Command {
    private LocalDateTime dateFilter = null;

    public ListCommand() {

    }

    public ListCommand(LocalDateTime date) {
        this.dateFilter = date;
    }

    @Override
    public void execute(TextUi ui, TaskList taskList) throws DukeException {
        List<String> listItems;
        if (this.dateFilter == null) {
            listItems = Arrays.asList(taskList.toString().split("\n"));
        } else {
            TaskList filteredTaskList = taskList.filterByDate(dateFilter);
            listItems = Arrays.asList(filteredTaskList.toString().split("\n"));
        }
        ui.printFromRed("Alright, here are your tasks.\n");
        ui.printWithBuffer("----------\n");
        ui.printWithBuffer("TOTAL: " + (taskList.getTasksCount() == 0 ? "0" : listItems.size()) + " tasks\n");
        ui.printWithBuffer("----------\n");
        listItems.forEach((item) -> ui.printWithBuffer(item + "\n"));
        ui.printWithBuffer("----------\n");
    }
}
