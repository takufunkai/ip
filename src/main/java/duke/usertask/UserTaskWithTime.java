package duke.usertask;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import duke.utils.Utils;

/**
 * The UserTaskWithTime class encapsulates Tasks that have some dateTime associated with them.
 */
public abstract class UserTaskWithTime extends UserTask {
    private static final DateTimeFormatter TO_DISPLAY_DATE_TIME_FORMATTER =
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
            throw new UserTaskException("Unable to parse date-time. "
                    + "Please ensure it is of the following format: "
                    + Utils.DATE_FORMAT + " " + Utils.TIME_FORMAT);
        }
    }

    /**
     * Checks if the UserTaskWithTime's associated dateTime is equal to the given date. Equality is determined by
     * whether they fall on the same calendar date, regardless of time.
     *
     * @param date The date to match this object against.
     * @return True if the date of this object is the same as the specified date, false if otherwise.
     * @throws UserTaskException If the specified date is of the wrong format.
     */
    public boolean isDated(LocalDateTime date) throws UserTaskException {
        try {
            return this.dateTime.toLocalDate().isEqual(date.toLocalDate());
        } catch (DateTimeException e) {
            throw new UserTaskException("Unable to parse date. "
                    + "Please ensure it is of the following format: "
                    + Utils.DATE_FORMAT);
        }
    }

    /**
     * {@inheritDoc}
     * Adds its dateTime in string Duke savable format to the DukeSaveFormat string.
     */
    @Override
    public String toDukeSaveFormat() {
        return super.toDukeSaveFormat() + "|" + Utils.formatLocalDateTime(this.dateTime);
    }

    /**
     * {@inheritDoc}
     * Add its current dateTime to the display string.
     */
    @Override
    public String toString() {
        return "%s" + super.toString()
                + " (%s: " + Utils.formatLocalDateTime(this.dateTime, TO_DISPLAY_DATE_TIME_FORMATTER) + ")";
    }
}
