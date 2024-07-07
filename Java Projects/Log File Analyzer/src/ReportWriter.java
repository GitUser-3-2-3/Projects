import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class ReportWriter {

    public static void writeSummaryReport(
        Map<String, Long> summary, List<LogEntry> detailedEntries, String filePath
    ) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {

            for (Map.Entry<String, Long> entry : summary.entrySet()) {
                writer.write(entry.getKey() + ": " + entry.getValue());
                writer.newLine();
            }

            writer.newLine();
            writer.write("Detailed Log Entries: \n");
            writer.newLine();

            for (LogEntry entry : detailedEntries) {
                writer.write(entry.toString());
                writer.newLine();
            }
        }
    }
}
