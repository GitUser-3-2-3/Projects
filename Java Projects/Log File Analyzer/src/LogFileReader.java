import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LogFileReader {

    public static List<String> readLogFiles(String filePath) throws IOException {
        return Files.readAllLines(Paths.get(filePath));
    }

    public static List<Path> getLogFiles(String directoryPath) throws IOException {
        try (Stream<Path> paths = Files.walk(Paths.get(directoryPath))) {
            return paths.filter(Files::isRegularFile)
                .collect(Collectors.toList());
        }
    }
}
