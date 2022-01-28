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

public abstract class Command {
    public enum CommandNames {
        FIND, LIST, TODO, DEADLINE, EVENT, MARK, UNMARK, DELETE, BYE;
    }

    public abstract void execute(TextUi ui, TaskList taskList) throws DukeException;

    public boolean isExit() {
        return false;
    }

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
            return new TodoCommand(userInputSplit[1]);
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