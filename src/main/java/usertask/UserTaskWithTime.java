package usertask;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public abstract class UserTaskWithTime extends UserTask {
    private final static String DATE_FORMAT = "dd-MM-yyyy";
    private final static String TIME_FORMAT = "HH:mm";
    private final static DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern(DATE_FORMAT);
    private final static DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern(TIME_FORMAT);

    private final LocalDateTime dateTime;

    UserTaskWithTime(String name, String dateTime) throws UserTaskException {
        super(name);
        String[] dateTimeSplit = dateTime.split(" ");
        try {
            LocalDate date = LocalDate.parse(dateTimeSplit[0], DATE_FORMATTER);
            if (dateTimeSplit.length > 1) {
                this.dateTime = date.atTime(
                        LocalTime.parse(dateTimeSplit[1], TIME_FORMATTER));
            } else {
                this.dateTime = date.atStartOfDay();
            }
        } catch (DateTimeParseException e) {
            throw new UserTaskException("Unable to parse date-time. " +
                    "Please ensure it is of the following format: " +
                    DATE_FORMAT + " " + TIME_FORMAT);
        }
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a");
        return "%s" + super.toString() + " (%s: " + this.dateTime.format(formatter) + ")";
    }
}
