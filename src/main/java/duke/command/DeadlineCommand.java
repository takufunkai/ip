package duke.command;

import duke.textui.TextUi;
import duke.usertask.Deadline;
import duke.usertask.TaskList;
import duke.usertask.UserTask;
import duke.usertask.UserTaskException;

public class DeadlineCommand extends Command {
    private final String description;
    private final String deadlineDateTime;

    public DeadlineCommand(String description, String deadlineDateTime) {
        this.description = description;
        this.deadlineDateTime = deadlineDateTime;
    }

    @Override
    public void execute(TextUi ui, TaskList taskList) {
        try {
            UserTask task = new Deadline(description, deadlineDateTime);
            taskList.addTask(task);
            ui.printFromRed("Added task #" + (taskList.getTasksCount()) + ": " + task + "\n");
        } catch (UserTaskException e) {
            // TODO: throw a CommandException here regarding invalid dt fmt
            // replace following:
            System.out.println(e.getMessage());
        }

    }
}
