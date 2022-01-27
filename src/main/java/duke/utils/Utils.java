package duke.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public final class Utils {
    public final static String DATE_FORMAT = "dd-MM-yyyy";
    public final static String TIME_FORMAT = "HH:mm";
    private final static DateTimeFormatter COMMAND_DATE_FORMATTER =
            DateTimeFormatter.ofPattern(DATE_FORMAT);
    private final static DateTimeFormatter COMMAND_DATE_TIME_FORMATTER =
            DateTimeFormatter.ofPattern(DATE_FORMAT + " " + TIME_FORMAT);

    public static String colourStringRed(String out) {
        String ANSI_RED = "\u001B[31m";
        String ANSI_RESET = "\u001B[0m";
        return ANSI_RED + out + ANSI_RESET;
    }

    public static String formatLocalDateTime(LocalDateTime ldt) {
        return ldt.format(COMMAND_DATE_TIME_FORMATTER);
    }

    public static String formatLocalDateTime(LocalDateTime ldt, DateTimeFormatter formatter) {
        return ldt.format(formatter);
    }

    public static LocalDateTime parseToLocalDateTime(String date, String time) {
        return LocalDateTime.parse(date + " " + time, COMMAND_DATE_TIME_FORMATTER);
    }

    public static LocalDateTime parseToLocalDateTime(String date) {
        return LocalDate.parse(date, COMMAND_DATE_FORMATTER).atStartOfDay();
    }
}
