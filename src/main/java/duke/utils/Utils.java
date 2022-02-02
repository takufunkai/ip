package duke.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public final class Utils {
    /**
     * The date string format of date-time objects that is understandable and savable by the chat-bot.
     */
    public static final String DATE_FORMAT = "dd-MM-yyyy";
    /**
     * The time string format of date-time objects that is understandable and savable by the chat-bot.
     */
    public static final String TIME_FORMAT = "HH:mm";
    private static final DateTimeFormatter COMMAND_DATE_FORMATTER =
            DateTimeFormatter.ofPattern(DATE_FORMAT);
    private static final DateTimeFormatter COMMAND_DATE_TIME_FORMATTER =
            DateTimeFormatter.ofPattern(DATE_FORMAT + " " + TIME_FORMAT);

    /**
     * Returns the string representation of the specified LocalDateTime, formatted by the specified formatter.
     *
     * @param ldt The LocalDateTime object to format to string.
     * @param formatter The formatter to apply onto ldt.
     * @return The string representation of the LocalDateTime that is formatted by the given formatter.
     */
    public static String formatLocalDateTime(LocalDateTime ldt, DateTimeFormatter formatter) {
        return ldt.format(formatter);
    }

    /**
     * Returns the string representation of the specified LocalDateTime, formatted by the default formatter that
     * the chat-bot understands and is able to save.
     *
     * @param ldt The LocalDateTime object to format to string.
     * @return The string representation of the LocalDateTime that is formatted by the given formatter.
     */
    public static String formatLocalDateTime(LocalDateTime ldt) {
        return ldt.format(COMMAND_DATE_TIME_FORMATTER);
    }

    /**
     * Parses the given date and time in string format into LocalDateTime. The specified date and time <b>must</b>
     * be the correct format that the chat-bot understands.
     *
     * @param date The date to be parsed.
     * @param time The time to be parsed.
     * @return The LocalDateTime that is parsed through the specified strings.
     */
    public static LocalDateTime parseToLocalDateTime(String date, String time) {
        return LocalDateTime.parse(date + " " + time, COMMAND_DATE_TIME_FORMATTER);
    }

    /**
     * Parses the given date in string format into LocalDateTime. The specified date<b>must</b>
     * be the correct format that the chat-bot understands. The LocalDateTime returned is automatically set at
     * time = 00:00.
     *
     * @param date The date to be parsed.
     * @return The LocalDateTime that is parsed through the specified strings.
     */
    public static LocalDateTime parseToLocalDateTime(String date) {
        return LocalDate.parse(date, COMMAND_DATE_FORMATTER).atStartOfDay();
    }
}
