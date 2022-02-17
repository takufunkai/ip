package duke.command.usertask;

import duke.DukeException;
import duke.usertask.TaskList;
import duke.utils.DukeResponse;

/**
 * FindCommand handles the necessary arguments for a filter operation on the TaskList, specifically a filter on the
 * names of the TaskList items. It receives 2 mandatory arguments, <code>description</code> and
 * <code>eventDateTime</code>, which are required in the creation of an Event task object.
 */
public class FindCommand extends UserTaskCommand {
    private final String search;


    /**
     * Stores the necessary argument for the search operation.
     *
     * @param search The search query that is to be matched against.
     */
    protected FindCommand(String search) {
        this.search = search;
    }

    /**
     * Searches for and returns items which name matches the {@code search} parameter supplied by the user.
     *
     * @throws DukeException Thrown if the filter did not succeed.
     * @return The response of the result of the execution.
     */
    @Override
    public DukeResponse execute() throws DukeException {
        TaskList filteredTaskList = this.tasks.filterByName(this.search);
        ListCommand lc = new ListCommand();
        lc.supply(tasksStorage, filteredTaskList);
        lc.changeListMessage("Alright, here are your matching tasks: ");
        return lc.execute();
    }
}
