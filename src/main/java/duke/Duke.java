package duke;

import java.io.IOException;

import duke.command.Command;
import duke.storage.SaveHandler;
import duke.textui.TextUi;
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
    private final TextUi ui;
    private SaveHandler sv = null;

    /**
     * Creates a new Duke chat-bot instance.
     */
    private Duke() {
        this.ui = new TextUi();
        try {
            this.sv = new SaveHandler();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        this.tasks = new TaskList(100);
    }

    /**
     * Begins execution of the Duke chat-bot.
     */
    public void run() {
        ui.printGreeting();
        if (sv != null) {
            try {
                sv.restore(tasks);
            } catch (DukeException e) {
                ui.printFromRed("Oops, something went wrong: " + "\n");
                ui.printFromRed("** " + e.getMessage() + "\n");
            }
        }
        boolean isRunning = true;
        while (isRunning) {
            try {
                String userInput = ui.awaitInputFromUser();
                Command cmd = Command.parse(userInput);
                cmd.execute(ui, tasks);
                ui.printWithBuffer("\n");
                isRunning = !cmd.isExit();
            } catch (DukeException e) {
                ui.printFromRed("Oops, something went wrong: " + "\n");
                ui.printFromRed("** " + e.getMessage() + "\n");
            }
        }
        ui.printExitMessage();
        if (sv != null) {
            sv.save(tasks);
        }
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}
