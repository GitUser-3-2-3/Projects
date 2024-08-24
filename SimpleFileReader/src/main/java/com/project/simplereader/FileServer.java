package com.project.simplereader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FileServer {
    private static final int PORT = 5005;
    private final ExecutorService pool = Executors.newFixedThreadPool(10);
    InputOutput io = new InputOutput();

    public static void main(String[] args) {
        FileServer server = new FileServer();
        server.startServer();
    }

    public void startServer() {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("server is listening on port: " + PORT);

            //noinspection InfiniteLoopStatement
            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("new client connected");
                pool.execute(new ClientHandler(clientSocket, io));
            }
        } catch (IOException ioException) {
            System.out.println("Server Exception: " + ioException);
        }
    }

    private static class ClientHandler implements Runnable {
        private final Socket clientSocket;
        private final InputOutput io;

        private ClientHandler(Socket clientSocket, InputOutput io) {
            this.clientSocket = clientSocket;
            this.io = io;
        }

        @Override
        public void run() {
            try (
                BufferedReader input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter output = new PrintWriter(clientSocket.getOutputStream(), true)
            ) {
                String request;

                while ((request = input.readLine()) != null) {
                    if (request.equalsIgnoreCase("Read")) {
                        io.reader();
                        for (String line : io.getEverything().split(System.lineSeparator())) {
                            output.println(line);
                        }
                    } else if (request.startsWith("Write")) {
                        String[] parts = request.split(" ", 3);
                        String writerId = parts[1];
                        String content = parts[2];

                        io.setEverything(content);
                        io.writer(writerId);

                        output.println("File updated successfully");
                    } else {
                        output.println("unknown request");
                    }
                }
            } catch (IOException ioException) {
                System.out.println("Client Handler Exception: " + ioException);
            }
        }
    }
}
