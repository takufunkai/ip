package usertask;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<UserTask> tasks;

    public TaskList(int initialCapacity) {
        this.tasks = new ArrayList<>(initialCapacity);
    }

    public UserTask deleteTask(int number) {
        return this.tasks.remove(number - 1);
    }

    public int getTasksCount() {
        return tasks.size();
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            sb.append(i + 1).append(". ").append(tasks.get(i).toString());
        }
        return sb.toString();
    }
}
