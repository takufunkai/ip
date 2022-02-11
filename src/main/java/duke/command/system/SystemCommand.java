package duke.command.system;

import duke.command.Command;

/**
 * SystemCommand class handles the commands which relate to the Duke chat-bot system, instead of the logical
 * program execution. SystemCommands are commands such as 'Bye'.
 */
public abstract class SystemCommand extends Command {
    /**
     * Returns the SystemCommand executable.
     *
     * @param command The command enum supplied by the user.
     * @return The SystemCommand executable object.
     */
    public static SystemCommand parse(CommandNames command) {
        return new ByeCommand();
    }
}
