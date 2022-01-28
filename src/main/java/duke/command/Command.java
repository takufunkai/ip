package duke.command;

import duke.DukeException;
import duke.textui.TextUi;
import duke.usertask.TaskList;
import duke.utils.Utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Locale;

import static duke.utils.Utils.DATE_FORMAT;
import static duke.utils.Utils.TIME_FORMAT;

/**
 * <code>Command</code> is the abstract base class for all possible commands that <code>Duke</code> recognizes.
 * <p>
 * <code>Command</code> handles the parsing of user input by determining the command type given
 * and then validating the supplied arguments, if any.
 * <code>Command</code> will then return one of the executable subclass objects based on the
 * input given by the user.
 * <p>
 * All concrete implementations of Command should be able to <code>execute</code> based on the
 * arguments supplied during the instantiation of the subclass object in <code>Command::parse</code>.
 * They should be responsible for their entire expected behaviour within the <code>execute</code> method.
 */
public abstract class Command {
    /**
     * Enums for all possible valid commands that are allowed to be supplied by the user.
     */
    private enum CommandNames {
        FIND, LIST, TODO, DEADLINE, EVENT, MARK, UNMARK, DELETE, BYE
    }

    /**
     * Runs the expected behaviour of the specific command. We can assume that command-specific
     * arguments have been supplied during the instantiation of the <code>Command</code> subclass object.
     *
     * @param ui       The <code>TextUi</code> object being used by <code>Duke</code>.
     * @param taskList The <code>TaskList</code> of the current user.
     * @throws DukeException Thrown if some invalid command was given, or the supplied arguments are invalid.
     */
    public abstract void execute(TextUi ui, TaskList taskList) throws DukeException;

    /**
     * Checks if the <code>Command</code> is an <code>ByeCommand</code>. Used by <code>Duke</code> to
     * determine if execution has been terminated by the user.
     *
     * @return true if and only if command is <code>ByeCommand</code>.
     */
    public boolean isExit() {
        return false;
    }

    /**
     * Static method that parses the input given by the user.
     * It checks if the supplied input is valid or not, before determining which <code>Command</code>
     * subclass object should be instantiated and returned. It also determines and validates
     * the arguments supplied to the specific <code>Command</code> by the user. If any of the arguments
     * are invalid, or required but not supplied, the object is not created and instead a DukeException will be thrown.
     *
     * @param input The entire string that was supplied by the user.
     * @return A <code>Command</code> object.
     * @throws DukeException Thrown if arguments supplied are invalid.
     */
    public static Command parse(String input) throws DukeException {
        if (input.isBlank()) {
            throw new DukeException("No command was given. Please specify a valid command!");
        }
        String[] userInputSplit = input.split("\\s+", 2);
        CommandNames cmd;
        try {
            cmd = CommandNames.valueOf(userInputSplit[0].toUpperCase(Locale.ROOT));
        } catch (IllegalArgumentException e) {
            throw new DukeException("I don't know what " + userInputSplit[0] + " means.");
        }
        switch (cmd) {
        case BYE:
            return new ByeCommand();
        case LIST:
            if (userInputSplit.length == 1 || userInputSplit[1].isBlank()) {
                return new ListCommand();
            }
            String[] listArgs = userInputSplit[1].split(" ");
            if (!listArgs[0].equalsIgnoreCase("date") || listArgs.length == 1) {
                throw new DukeException("Unknown parameter supplied to list command.");
            }
            try {
                LocalDateTime filterDate = Utils.parseToLocalDateTime(listArgs[1]);
                return new ListCommand(filterDate);
            } catch (DateTimeParseException e) {
                throw new DukeException(String.format("Failed to parse date %s. " +
                        "Please ensure it is of the following format: " +
                        DATE_FORMAT + " " + TIME_FORMAT, listArgs[1]));
            }
        case FIND:
            if (userInputSplit.length == 1 || userInputSplit[1].isBlank()) {
                throw new DukeException("Please indicate a valid word to match the task names.");
            }
            return new FindCommand(userInputSplit[1]);
        case MARK:
            if (userInputSplit.length == 1) {
                throw new DukeException("Please indicate a task item number to mark");
            }
            int taskNumber;
            try {
                taskNumber = Integer.parseInt(userInputSplit[1]);
            } catch (NumberFormatException e) {
                throw new DukeException("Your tasks are identified by numbers! " +
                        "Please input a valid number.");
            }
            if (taskNumber <= 0) {
                throw new DukeException("Are you trying to be funny?");
            }
            return new MarkCommand(taskNumber);
        case UNMARK:
            if (userInputSplit.length == 1) {
                throw new DukeException("Please indicate a task item number to unmark");
            }
            try {
                taskNumber = Integer.parseInt(userInputSplit[1]);
            } catch (NumberFormatException e) {
                throw new DukeException("Your tasks are identified by numbers! " +
                        "Please input a valid number.");
            }
            if (taskNumber <= 0) {
                throw new DukeException("Are you trying to be funny?");
            }
            return new UnmarkCommand(taskNumber);
        case DELETE:
            if (userInputSplit.length == 1) {
                throw new DukeException("Please indicate a task item number to delete");
            }
            try {
                taskNumber = Integer.parseInt(userInputSplit[1]);
            } catch (NumberFormatException e) {
                throw new DukeException("Your tasks are identified by numbers! " +
                        "Please input a valid number.");
            }
            if (taskNumber <= 0) {
                throw new DukeException("Are you trying to be funny?");
            }
            return new DeleteCommand(taskNumber);
        case TODO:
            if (userInputSplit.length == 1) {
                throw new DukeException("ToDo items must have a description.");
            }
            return new ToDoCommand(userInputSplit[1]);
        case DEADLINE:
            if (userInputSplit.length == 1) {
                throw new DukeException("Deadline items must have a description and due date.\n");
            }
            String[] parsedInput = input.split(" /by ");
            if (parsedInput.length != 2) {
                throw new DukeException("Deadline items must have a description and due date.\n");
            }
            String taskName = parsedInput[0];
            String date = parsedInput[1];
            return new DeadlineCommand(taskName, date);
        case EVENT:
            if (userInputSplit.length == 1) {
                throw new DukeException("Event items must have a description and date.\n");
            }
            parsedInput = input.split(" /at ");
            if (parsedInput.length != 2) {
                throw new DukeException("Event items must have a description and date.\n");
            }
            taskName = parsedInput[0];
            date = parsedInput[1];
            return new EventCommand(taskName, date);
        default:
            throw new DukeException("Unknown command.");
        }
    }
}