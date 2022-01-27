package duke;

import duke.command.Command;
import duke.storage.SaveHandler;
import duke.textui.TextUi;
import duke.usertask.TaskList;

import java.io.IOException;

public class Duke {
    private final TaskList tasks;
    private final TextUi ui;
    private SaveHandler sv = null;

    public void run() {
        ui.printGreeting();
        if (sv != null) {
            try {
                sv.restore(tasks);
            } catch (DukeException e) {
                ui.printFromRed("Oops, something went wrong: ");
                ui.printFromRed("** " + e.getMessage() + "\n");
            }
        }
        boolean isRunning = true;
        while (isRunning) {
            try {
                String userInput = ui.awaitInputFromUser();
                Command cmd = Command.parse(userInput);
                cmd.execute(ui, tasks);
                isRunning = !cmd.isExit();
            } catch (DukeException e) {
                ui.printFromRed("Oops, something went wrong: ");
                ui.printFromRed("** " + e.getMessage() + "\n");
            }
        }
        ui.printExitMessage();
        if (sv != null) {
            sv.save(tasks);
        }
    }

    public Duke() {
        this.ui = new TextUi();
        try {
            this.sv = new SaveHandler();
        } catch (IOException e) {
            System.out.println(e.getMessage()); // TODO: explicitly handle IOException
        }
        this.tasks = new TaskList(100);
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}
