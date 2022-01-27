package duke.usertask;

public abstract class UserTask implements DukeSavable {
    private final String name;
    private boolean isDone;

    @Override
    public String toDukeSaveFormat() {
        return "%s|" + (isDone ? "1" : "0") + "|" + name;
    }

    UserTask(String name) {
        this.name = name;
        this.isDone = false;
    }

    public void setDone() {
        this.isDone = true;
    }

    public void setUndone() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        return "[" + (isDone ? "X" : " ") + "] " + this.name;
    }
}