package com.project.simplereader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Reader {
    public void reader() {
        try (
            BufferedReader reader = new BufferedReader(new FileReader("file.txt"))
        ) {
            StringBuilder builder = new StringBuilder();
            String line = reader.readLine();

            while (line != null) {
                builder.append(line);
                builder.append(System.lineSeparator());
                line = reader.readLine();
            }
            String everything = builder.toString();
            System.out.println(everything);
        } catch (IOException ioException) {
            System.out.println("Exception Occurred: " + ioException);
        }
    }
}
