package com.project.simplereader;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class InputOutput {
    private String everything;

   public void reader() {
        try (BufferedReader reader = new BufferedReader(
            new FileReader("File.txt"))
        ) {
            StringBuilder builder = new StringBuilder();
            String line = reader.readLine();

            while (line != null) {
                builder.append(line);
                builder.append(System.lineSeparator());
                line = reader.readLine();
            }
            everything = builder.toString();
        } catch (IOException ioException) {
            System.out.println("Exception Reading: " + ioException);
        }
    }

    public void writer() {
        Path filePath = Paths.get("FileWriter.txt");
        try {
            if (!Files.exists(filePath)) {
                Files.createFile(filePath);
            }
            try (
                BufferedWriter writer = Files.newBufferedWriter(filePath)
            ) {
                writer.write(everything);
            }
        } catch (IOException ioException) {
            System.out.println("Exception Writing: " + ioException);
        }
    }
}









