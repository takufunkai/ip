package duke.command;

import duke.DukeException;
import duke.textui.TextUi;
import duke.usertask.TaskList;

public class FindCommand extends Command {
    private final String search;

    public FindCommand(String search) {
        this.search = search;
    }

    @Override
    public void execute(TextUi ui, TaskList taskList) throws DukeException {
        TaskList filteredTaskList = taskList.filterByName(this.search);
        ListCommand lc = new ListCommand();
        lc.changeListMessage("Alright, here are your matching tasks: ");
        lc.execute(ui, filteredTaskList);
    }
}
