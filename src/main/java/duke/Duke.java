package duke;

import java.io.IOException;

import duke.command.Command;
import duke.storage.SaveHandler;
import duke.usertask.TaskList;

/**
 * Duke is a chat-bot program that is capable of logging tasks which are (optionally)
 * tagged with a deadline/occurrence date.
 * <p>
 * Duke automatically saves the tasks of each session upon successful termination
 * of the session, and restores these tasks on the next launch.
 */
public class Duke {
    private final TaskList tasks;
    private SaveHandler sv = null;

    /**
     * Creates a new Duke chat-bot instance.
     */
    public Duke() {
        try {
            this.sv = new SaveHandler();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        this.tasks = new TaskList(100);
        try {
            sv.restore(tasks);
        } catch (DukeException e) {
            System.out.println("Failed to restore saved tasks: " + e.getMessage());
        }
    }

    public String getGreeting() {
        return "Hello, I am Red from Among Us.\n\nWe are currently facing a crisis onboard -- there seems to be an "
                + "imposter among us...\n\nMy job is to handle chat requests, so although I might get murdered any "
                + "moment now...\n\n... How can I help you?";
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) throws DukeException {
        Command cmd = Command.parse(input);
        if (cmd.isExit()) {
            sv.save(tasks);
            return "EXIT";
        }
        return cmd.execute(tasks);
    }
}
