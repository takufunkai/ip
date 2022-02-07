package duke.command;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import duke.DukeException;
import duke.storage.SaveHandler;
import duke.usertask.TaskList;

/**
 * ListCommand handles outputting the entire list of tasks that a user has at the current point in time.
 * It has an optional argument field <code>dateFilter</code>, which will filter the list of tasks to output
 * based on whether they 1. have a <code>dateTime</code> field, and 2. the date of the field is equal to the specified
 * dateFilter supplied by the user. <b>Note:</b> time is irrelevant here, and filter works solely based on date.
 */
public class ListCommand extends Command {
    private LocalDateTime dateFilter = null;
    private String listMessage = "Alright, here are your tasks.";

    /**
     * Creates a ListCommand object that has no specified dateFilter.
     */
    public ListCommand() {

    }

    /**
     * Creates a ListCommand object that has some specified dateFilter.
     *
     * @param date The date that tasks should be filtered based on.
     */
    public ListCommand(LocalDateTime date) {
        this.dateFilter = date;
        this.listMessage = listMessage + "for the date: " + date.toString();
    }

    public void changeListMessage(String message) {
        this.listMessage = message;
    }

    /**
     * Prints out all the tasks that the user has logged so far in a neat table format. Metadata (the total tasks)
     * are printed out at the top of the table. If this object has some <code>dateFilter</code>, the tasks outputted
     * will be filtered based on the dateFilter supplied.
     *
     * @param taskList The <code>TaskList</code> of the current user.
     * @param saveHandler The SaveHandler used by Duke.
     * @throws DukeException Thrown if the filter did not succeed.
     */
    @Override
    public String execute(TaskList taskList, SaveHandler saveHandler) throws DukeException {
        List<String> listItems;
        if (this.dateFilter == null) {
            listItems = Arrays.asList(taskList.toString().split("\n"));
        } else {
            TaskList filteredTaskList = taskList.filterByDate(dateFilter);
            listItems = Arrays.asList(filteredTaskList.toString().split("\n"));
        }
        StringBuilder sb = new StringBuilder();
        sb.append(this.listMessage)
                .append("\n----------\nTOTAL: ")
                .append(taskList.getTasksCount() == 0 ? "0" : listItems.size())
                .append(" tasks\n----------\n");
        listItems.forEach((item) -> sb.append(item).append("\n"));
        return sb.toString();
    }
}
