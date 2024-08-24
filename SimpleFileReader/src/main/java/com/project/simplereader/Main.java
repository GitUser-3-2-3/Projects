package com.project.simplereader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {
    private static final int NUM_READERS = 5;
    private static final int NUM_WRITERS = 3;

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(NUM_READERS + NUM_WRITERS);

        // simulate multiple writers
        for (int i = 0; i < NUM_WRITERS; i++) {
            int writerId = i + 1;
            executor.execute(() -> {
                try (
                    Socket clientSocket = new Socket("localhost", 5005);
                    BufferedReader input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                    PrintWriter output = new PrintWriter(clientSocket.getOutputStream(), true)
                ) {
                    String contentToWrite = " " + writerId;
                    output.println("Write " + contentToWrite);

                    String response = input.readLine();
                    System.out.println("Writer " + writerId + " - Server Response: " + response);
                } catch (IOException ioException) {
                    System.out.println("Writer " + writerId + " - Exception " + ioException);
                }
            });
        }

        // simulate multiple readers.
        for (int i = 0; i < NUM_READERS; i++) {
            int readerId = i + 1;
            executor.execute(() -> {
                try (
                    Socket clientSocket = new Socket("localhost", 5005);
                    BufferedReader input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                    PrintWriter output = new PrintWriter(clientSocket.getOutputStream(), true)
                ) {
                    output.println("Read");
                    System.out.println("Reader: " + readerId + " - File Content: ");

                    String fileContent;
                    while ((fileContent = input.readLine()) != null) {
                        System.out.println("Reader: " + readerId + ": " + fileContent);
                    }
                } catch (IOException ioException) {
                    System.out.println("Exception while reading: " + ioException);
                }
            });
        }

        // Shutdown the executor service
        executor.shutdown();
        try {
            if (!executor.awaitTermination(60, TimeUnit.SECONDS)) {
                executor.shutdownNow();
            }
        } catch (InterruptedException itrException) {
            System.out.println("Exception in termination: " + itrException);
        }
    }
}









