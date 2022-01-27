package duke.command;

import duke.textui.TextUi;
import duke.usertask.Event;
import duke.usertask.TaskList;
import duke.usertask.UserTask;
import duke.usertask.UserTaskException;

public class EventCommand extends Command {
    private final String description;
    private final String eventDateTime;

    public EventCommand(String description, String eventDateTime) {
        this.description = description;
        this.eventDateTime = eventDateTime;
    }

    @Override
    public void execute(TextUi ui, TaskList taskList) {
        try {
            UserTask task = new Event(description, eventDateTime);
            taskList.addTask(task);
            ui.printFromRed("Added task #" + (taskList.getTasksCount()) + ": " + task + "\n");
        } catch (UserTaskException e) {
            //TODO: replace this with commandException
            System.out.println(e.getMessage());
        }
    }
}
