import java.util.List;

public class LogFilter {

    public static List<LogEntry> filterByLogLevel(
        List<LogEntry> entries, String logLevel
    ) {
        return entries.stream().filter(
            entry -> entry.getLogLevel().equalsIgnoreCase(logLevel)
        ).toList();
    }
}
