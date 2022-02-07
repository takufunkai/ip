package duke.command;

import duke.DukeException;
import duke.storage.SaveHandler;
import duke.usertask.TaskList;

/**
 * FindCommand handles the necessary arguments for a filter operation on the TaskList, specifically a filter on the
 * names of the TaskList items. It receives 2 mandatory arguments, <code>description</code> and
 * <code>eventDateTime</code>, which are required in the creation of an Event task object.
 */
public class FindCommand extends Command {
    private final String search;


    /**
     * Stores the necessary argument for the search operation.
     *
     * @param search The search query that is to be matched against.
     */
    public FindCommand(String search) {
        this.search = search;
    }

    /**
     * Searches for and returns items which name matches the {@code search} parameter supplied by the user.
     *
     * @param taskList The <code>TaskList</code> of the current user.
     * @param saveHandler The SaveHandler used by Duke.
     * @throws DukeException Thrown if the filter did not succeed.
     */
    @Override
    public String execute(TaskList taskList, SaveHandler saveHandler) throws DukeException {
        TaskList filteredTaskList = taskList.filterByName(this.search);
        ListCommand lc = new ListCommand();
        lc.changeListMessage("Alright, here are your matching tasks: ");
        return lc.execute(filteredTaskList, saveHandler);
    }
}
