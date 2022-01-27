package usertask;

public class ToDo extends UserTask {
    public ToDo(String name) {
        super(name);
    }

    @Override
    public String toDukeSaveFormat() {
        return String.format(super.toDukeSaveFormat(), "T");
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
