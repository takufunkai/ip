package duke.usertask;

import duke.utils.Utils;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static duke.utils.Utils.DATE_FORMAT;
import static duke.utils.Utils.TIME_FORMAT;

public abstract class UserTaskWithTime extends UserTask implements DukeSavable {
    private final static DateTimeFormatter TO_DISPLAY_DATE_TIME_FORMATTER =
            DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a");

    private final LocalDateTime dateTime;

    UserTaskWithTime(String name, String dateTime) throws UserTaskException {
        super(name);
        String[] dateTimeSplit = dateTime.split(" ");
        try {
            this.dateTime = dateTimeSplit.length > 1
                    ? Utils.parseToLocalDateTime(dateTimeSplit[0], dateTimeSplit[1])
                    : Utils.parseToLocalDateTime(dateTimeSplit[0]);
        } catch (DateTimeException e) {
            throw new UserTaskException("Unable to parse date-time. " +
                    "Please ensure it is of the following format: " +
                    DATE_FORMAT + " " + TIME_FORMAT);
        }
    }

    public boolean isDated(LocalDateTime date) throws UserTaskException {
        try {
            return this.dateTime.toLocalDate().isEqual(date.toLocalDate());
        } catch (DateTimeException e) {
            throw new UserTaskException("Unable to parse date. " +
                    "Please ensure it is of the following format: " +
                    DATE_FORMAT);
        }
    }

    @Override
    public String toDukeSaveFormat() {
        return super.toDukeSaveFormat() + "|" + Utils.formatLocalDateTime(this.dateTime);
    }

    @Override
    public String toString() {
        return "%s" + super.toString() + " (%s: " + Utils.formatLocalDateTime(this.dateTime, TO_DISPLAY_DATE_TIME_FORMATTER) + ")";
    }
}
