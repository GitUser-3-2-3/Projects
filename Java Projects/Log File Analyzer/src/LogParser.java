import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LogParser {

    private static final DateTimeFormatter FORMATTER =
        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static LogEntry parseLogEntry(String logLine) {
        String[] parts = logLine.split(" ", 3);

        LocalDateTime timestamp = null;
        try {
            timestamp = LocalDateTime.parse(parts[0] + " " + parts[1], FORMATTER);
        } catch (Exception e) {
            System.out.println("Error occurred: " + e);
        }
        String logLevel = parts[2].split(":")[0];
        String message = parts[2].split(":")[1].trim();

        return new LogEntry(timestamp, logLevel, message);
    }
}
