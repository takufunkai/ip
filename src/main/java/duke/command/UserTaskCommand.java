package duke.command;

import static duke.utils.Utils.DATE_FORMAT;
import static duke.utils.Utils.TIME_FORMAT;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import duke.DukeException;
import duke.command.usertask.DeadlineCommand;
import duke.command.usertask.DeleteCommand;
import duke.command.usertask.EventCommand;
import duke.command.usertask.FindCommand;
import duke.command.usertask.ListCommand;
import duke.command.usertask.MarkCommand;
import duke.command.usertask.ToDoCommand;
import duke.command.usertask.UnmarkCommand;
import duke.storage.SaveHandler;
import duke.usertask.TaskList;
import duke.utils.Utils;

public abstract class UserTaskCommand extends Command {
    protected SaveHandler saveHandler;
    protected TaskList tasks;

    protected UserTaskCommand() {
    }

    public UserTaskCommand supply(SaveHandler saveHandler, TaskList tasks) {
        this.saveHandler = saveHandler;
        this.tasks = tasks;
        return this;
    }

    public static UserTaskCommand parse(
            CommandNames command, String arguments, SaveHandler savehandler, TaskList tasks) throws DukeException {
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
