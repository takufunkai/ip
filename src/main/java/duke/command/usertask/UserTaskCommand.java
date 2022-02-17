package duke.command.usertask;

import static duke.utils.Utils.DATE_FORMAT;
import static duke.utils.Utils.TIME_FORMAT;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import duke.DukeException;
import duke.command.Command;
import duke.storage.Storage;
import duke.usertask.TaskList;
import duke.utils.Utils;

/**
 * UserTaskCommand encapsulates the information required for commands that relate to UserTasks, for example the
 * TaskList that Duke maintains, and the TasksStorage. It also handles the parsing of a given command to return a proper
 * executable command.
 */
public abstract class UserTaskCommand extends Command {
    protected Storage tasksStorage;
    protected TaskList tasks;

    protected UserTaskCommand() {
    }

    /**
     * Supplies to the UserTaskCommand the necessary fields for proper execution. This is necessary, since we want
     * the Command class to handle the dissemination of necessary data to the correct Command subtype, i.e. this is
     * to prevent Duke from having to handle the type of command it receives, and then to pass it the correct data
     * structure.
     *
     * @param tasksStorage The TasksStorage that Duke uses.
     * @param tasks The tasks that Duke is maintaining.
     * @return This UserTaskCommand object.
     */
    public UserTaskCommand supply(Storage tasksStorage, TaskList tasks) {
        this.tasksStorage = tasksStorage;
        this.tasks = tasks;
        return this;
    }

    /**
     * Reads a given user command, creates the command object and supplies the arguments to the command object,
     * then returns it.
     *
     * @param command The user given command enum.
     * @param arguments The arguments that were supplied by the user.
     * @return The correct executable command object.
     * @throws DukeException If the argument supplied is invalid.
     */
    public static UserTaskCommand parse(
            CommandNames command, String arguments) throws DukeException {
        boolean noArgumentsSupplied = arguments == null || arguments.isBlank();

        switch (command) {
        case LIST:
            if (noArgumentsSupplied) {
                return new ListCommand();
            }

            String[] listCommandArguments = arguments.split(" ");
            if (listCommandArguments.length == 1) {
                throw new DukeException("Insufficient parameters supplied!");
            }

            String delimiter = listCommandArguments[0];
            if (!delimiter.equalsIgnoreCase("/date")) {
                throw new DukeException("Unknown parameter supplied to list command.");
            }

            try {
                LocalDateTime filterDate = Utils.parseToLocalDateTime(listCommandArguments[1]);
                return new ListCommand(filterDate);
            } catch (DateTimeParseException e) {
                throw new DukeException(String.format("Failed to parse date %s. "
                        + "Please ensure it is of the following format: "
                        + DATE_FORMAT + " " + TIME_FORMAT, listCommandArguments[1]));
            }
        case FIND:
            if (noArgumentsSupplied) {
                throw new DukeException("Please indicate a valid word to match the task names.");
            }

            return new FindCommand(arguments);
        case MARK:
            if (noArgumentsSupplied) {
                throw new DukeException("Please indicate a task item number to mark");
            }

            int taskNumber;
            try {
                taskNumber = Integer.parseInt(arguments);
            } catch (NumberFormatException e) {
                throw new DukeException("Your tasks are identified by numbers! "
                        + "Please input a valid number.");
            }
            if (taskNumber <= 0) {
                throw new DukeException("Are you trying to be funny?");
            }

            return new MarkCommand(taskNumber);
        case UNMARK:
            if (noArgumentsSupplied) {
                throw new DukeException("Please indicate a task item number to unmark");
            }

            try {
                taskNumber = Integer.parseInt(arguments);
            } catch (NumberFormatException e) {
                throw new DukeException("Your tasks are identified by numbers! "
                        + "Please input a valid number.");
            }
            if (taskNumber <= 0) {
                throw new DukeException("Are you trying to be funny?");
            }

            return new UnmarkCommand(taskNumber);
        case DELETE:
            if (noArgumentsSupplied) {
                throw new DukeException("Please indicate a task item number to delete");
            }

            try {
                taskNumber = Integer.parseInt(arguments);
            } catch (NumberFormatException e) {
                throw new DukeException("Your tasks are identified by numbers! "
                        + "Please input a valid number.");
            }
            if (taskNumber <= 0) {
                throw new DukeException("Are you trying to be funny?");
            }

            return new DeleteCommand(taskNumber);
        case TODO:
            if (noArgumentsSupplied) {
                throw new DukeException("ToDo items must have a description.");
            }

            return new ToDoCommand(arguments);
        case DEADLINE:
            if (noArgumentsSupplied) {
                throw new DukeException("Deadline items must have a description and due date.\n");
            }

            String[] delimitedDeadlineArguments = arguments.split(" /by ");
            if (delimitedDeadlineArguments.length != 2) {
                throw new DukeException("Deadline items must have a description and due date.\n");
            }

            String deadlineTaskName = delimitedDeadlineArguments[0];
            String deadlineDate = delimitedDeadlineArguments[1];
            return new DeadlineCommand(deadlineTaskName, deadlineDate);
        case EVENT:
            if (noArgumentsSupplied) {
                throw new DukeException("Event items must have a description and date.\n");
            }

            String[] delimitedEventArguments = arguments.split(" /at ");
            if (delimitedEventArguments.length != 2) {
                throw new DukeException("Event items must have a description and date.\n");
            }
            String eventTaskName = delimitedEventArguments[0];
            String eventDate = delimitedEventArguments[1];
            return new EventCommand(eventTaskName, eventDate);
        default:
            throw new DukeException("Unknown command.");
        }
    }
}
