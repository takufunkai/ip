package duke.command;

import duke.textui.TextUi;
import duke.usertask.TaskList;

import java.time.LocalDateTime;

public class ListCommand extends Command {
    private LocalDateTime dateTimeFilter = null;

    public ListCommand() {

    }

    public ListCommand(String time) {
        this.dateTimeFilter = LocalDateTime.parse(time); // TODO: ensure correct parse format
    }

    @Override
    public void execute(TextUi ui, TaskList taskList) {
        if (this.dateTimeFilter == null) {
            System.out.println(taskList);
            return;
        }
        TaskList filteredTaskList = taskList.filterByDate(dateTimeFilter);
        System.out.println(filteredTaskList); // TODO: format with ui object
    }
}
