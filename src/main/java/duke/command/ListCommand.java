package duke.command;

import duke.DukeException;
import duke.textui.TextUi;
import duke.usertask.TaskList;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

public class ListCommand extends Command {
    private LocalDateTime dateFilter = null;
    private String listMessage = "Alright, here are your tasks.";

    public ListCommand() {

    }

    public ListCommand(LocalDateTime date) {
        this.dateFilter = date;
        this.listMessage = listMessage + "for the date: " + date.toString();
    }

    public void changeListMessage(String message) {
        this.listMessage = message;
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
        ui.printFromRed(this.listMessage + "\n");
        ui.printWithBuffer("----------\n");
        ui.printWithBuffer("TOTAL: " + listItems.size() + " tasks\n");
        ui.printWithBuffer("----------\n");
        listItems.forEach((item) -> ui.printWithBuffer(item + "\n"));
        ui.printWithBuffer("----------\n");
    }
}
