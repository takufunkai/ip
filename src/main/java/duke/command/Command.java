package duke.command;

import java.util.EnumSet;
import java.util.Locale;

import duke.DukeException;
import duke.client.ClientList;
import duke.command.system.ByeCommand;
import duke.command.system.SystemCommand;
import duke.command.usertask.UserTaskCommand;
import duke.storage.TasksStorage;
import duke.usertask.TaskList;
import duke.utils.DukeResponse;

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
    public enum CommandNames {
        FIND, LIST, TODO, DEADLINE, EVENT, MARK, UNMARK, DELETE, BYE,
    }

    private static final EnumSet<CommandNames> userTaskCommand = EnumSet.of(
            CommandNames.FIND, CommandNames.LIST, CommandNames.DEADLINE, CommandNames.EVENT, CommandNames.DELETE,
            CommandNames.MARK, CommandNames.UNMARK, CommandNames.TODO
    );

    private static final EnumSet<CommandNames> systemCommand = EnumSet.of(CommandNames.BYE);

    /**
     * Executes the expected behaviour of the class.
     *
     * @return The response string of the execution.
     * @throws DukeException If some argument supplied to the command is invalid.
     */
    public abstract DukeResponse execute() throws DukeException;

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
    public static Command parse(
            String input, TaskList tasks, ClientList clients, TasksStorage tasksStorage) throws DukeException {
        assert !input.isBlank() : "Input given should not be blank";

        String[] inputStrings = input.split("\\s+", 2);
        String specifiedCommand = inputStrings[0];

        CommandNames command;
        try {
            command = CommandNames.valueOf(specifiedCommand.toUpperCase(Locale.ROOT));
        } catch (IllegalArgumentException e) {
            throw new DukeException("I don't know what " + specifiedCommand + " means.");
        }

        String arguments = inputStrings.length == 1 ? null : inputStrings[1];

        if (userTaskCommand.contains(command)) {
            return UserTaskCommand
                    .parse(command, arguments)
                    .supply(tasksStorage, tasks);
        } else if (systemCommand.contains(command)) {
            return SystemCommand.parse(command);
        } else {
            return new ByeCommand(); // TODO: Handle edge case where command is valid but doesn't belong anywhere.
        }
    }
}
