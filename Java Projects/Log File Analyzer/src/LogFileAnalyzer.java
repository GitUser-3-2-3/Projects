import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

public class LogFileAnalyzer {
    public static void main(String[] args) throws IOException {

        String directoryPath = "example.txt";
        String reportOutputPath = "output.txt";

        List<Path> logFiles = LogFileReader.getLogFiles(directoryPath);

        for (Path logFile : logFiles) {
            List<String> logLines = LogFileReader.readLogFiles(logFile.toString());

            List<LogEntry> logEntries = logLines.stream()
                .map(LogParser::parseLogEntry)
                .toList();

            List<LogEntry> errorLogs = LogFilter.filterByLogLevel(logEntries, "ERROR");
            Map<String, Long> summary = LogSummary.summarizeByLogLevel(errorLogs);

            ReportWriter.writeSummaryReport(summary, errorLogs, reportOutputPath);
        }
    }
}