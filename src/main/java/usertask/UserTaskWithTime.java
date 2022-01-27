package usertask;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public abstract class UserTaskWithTime extends UserTask implements DukeSavable {
    private final static String DATE_FORMAT = "dd-MM-yyyy";
    private final static String TIME_FORMAT = "HH:mm";
    private final static DateTimeFormatter PARSE_DATE_FORMATTER = DateTimeFormatter.ofPattern(DATE_FORMAT);
    private final static DateTimeFormatter PARSE_TIME_FORMATTER = DateTimeFormatter.ofPattern(TIME_FORMAT);
    private final static DateTimeFormatter PARSE_DATE_TIME_FORMATTER =
            DateTimeFormatter.ofPattern(DATE_FORMAT + " " + TIME_FORMAT);
    private final static DateTimeFormatter DISPLAY_DATE_TIME_FORMATTER =
            DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a");

    private final LocalDateTime dateTime;

    UserTaskWithTime(String name, String dateTime) throws UserTaskException {
        super(name);
        String[] dateTimeSplit = dateTime.split(" ");
        try {
            LocalDate date = LocalDate.parse(dateTimeSplit[0], PARSE_DATE_FORMATTER);
            if (dateTimeSplit.length > 1) {
                this.dateTime = date.atTime(
                        LocalTime.parse(dateTimeSplit[1], PARSE_TIME_FORMATTER));
            } else {
                this.dateTime = date.atStartOfDay();
            }
        } catch (DateTimeException e) {
            throw new UserTaskException("Unable to parse date-time. " +
                    "Please ensure it is of the following format: " +
                    DATE_FORMAT + " " + TIME_FORMAT);
        }
    }

    public boolean isDated(String date) throws UserTaskException {
        try {
            return this.dateTime.toLocalDate().isEqual(LocalDate.parse(date, PARSE_DATE_FORMATTER));
        } catch (DateTimeException e) {
            throw new UserTaskException("Unable to parse date. " +
                    "Please ensure it is of the following format: " +
                    DATE_FORMAT);
        }
    }

    @Override
    public String toDukeSaveFormat() {
        return super.toDukeSaveFormat() + "|" + this.dateTime.format(PARSE_DATE_TIME_FORMATTER);
    }

    @Override
    public String toString() {
        return "%s" + super.toString() + " (%s: " + this.dateTime.format(DISPLAY_DATE_TIME_FORMATTER) + ")";
    }
}
