package duke.command;

public abstract class SystemCommand extends Command {
    protected static SystemCommand parse(CommandNames command) {
        return new ByeCommand();
    }
}
