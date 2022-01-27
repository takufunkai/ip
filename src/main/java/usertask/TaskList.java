package usertask;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


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

    @Override
    public String toDukeSaveFormat() {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < tasks.size(); i++) {
            sb.append(tasks.get(i).toDukeSaveFormat()).append("\n");
        }
        return sb.toString();
    }

    public TaskList filterByDate(String date) {
        List<UserTask> filteredList = tasks
                .stream()
                .filter((task) -> {
                    try {
                        return task instanceof UserTaskWithTime && ((UserTaskWithTime) task).isDated(date);
                    } catch (UserTaskException e) {
                        System.out.println("Failed to get filtered tasks: " + e.getMessage() + date + "ok");
                    }
                    return false;
                })
                .collect(Collectors.toList());
        TaskList filteredTaskList = new TaskList(filteredList.size());
        filteredTaskList.tasks.addAll(filteredList);
        return filteredTaskList;
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
