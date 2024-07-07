import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class LogSummary {

    public static Map<String, Long> summarizeByLogLevel(List<LogEntry> entries) {
        return entries.stream().collect(
            Collectors.groupingBy(LogEntry::getLogLevel, Collectors.counting())
        );
    }
}
