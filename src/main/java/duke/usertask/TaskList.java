package duke.usertask;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import duke.DukeException;

public class TaskList implements DukeSavable {
    private final List<UserTask> tasks;

    public TaskList(int initialCapacity) {
        this.tasks = new ArrayList<>(initialCapacity + 1);
        this.tasks.add(null); // Index 0 is empty
    }

    public UserTask deleteTask(int number) {
        return this.tasks.remove(number);
    }

    public int getTasksCount() {
        return tasks.size() - 1; // Account for empty index 0
    }

    public UserTask markTask(int number) {
        UserTask targetTask = this.tasks.get(number);
        targetTask.setDone();
        return targetTask;
    }

    public UserTask unmarkTask(int number) {
        UserTask targetTask = this.tasks.get(number);
        targetTask.setUndone();
        return targetTask;
    }

    public void addTask(UserTask task) {
        this.tasks.add(task);
    }

    public void addTask(UserTask task, boolean done) {
        if (done) {
            task.setDone();
        }
        this.tasks.add(task);
    }

    public TaskList filterByName(String taskName) {
        TaskList filteredTaskList = new TaskList(100);
        filteredTaskList.tasks.addAll(
                this.tasks.subList(1, tasks.size())
                        .stream()
                        .filter((task) -> task.nameContains(taskName))
                        .collect(Collectors.toList()));
        return filteredTaskList;
    }

    public TaskList filterByDate(LocalDateTime date) throws DukeException {
        TaskList filteredTaskList = new TaskList(100);
        try {
            for (UserTask currTask : tasks) {
                if (currTask instanceof UserTaskWithTime
                        && ((UserTaskWithTime) currTask).isDated(date)) {
                    filteredTaskList.addTask(currTask);
                }
            }
        } catch (UserTaskException e) {
            throw new DukeException("Failed to get filtered tasks: " + e.getMessage());
        }
        return filteredTaskList;
    }

    @Override
    public String toDukeSaveFormat() {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < tasks.size(); i++) {
            sb.append(tasks.get(i).toDukeSaveFormat()).append("\n");
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < tasks.size(); i++) {
            sb.append(i).append(". ").append(tasks.get(i).toString()).append("\n");
        }
        return sb.toString();
    }
}
