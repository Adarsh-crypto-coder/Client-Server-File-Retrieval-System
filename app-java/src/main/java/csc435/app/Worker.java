package csc435.app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class Worker implements Runnable {
    private IndexStore store;
    private Socket socket;
    private InetAddress clientAddress;
    private Thread thread;

    public Worker(IndexStore store, Socket socket) {
        this.store = store;
        this.socket = socket;
        this.clientAddress = socket.getInetAddress();
    }

    public String getIpAddress() {
        return clientAddress.getHostAddress();
    }

    @Override
    public void run() {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            String command;
            while ((command = in.readLine()) != null) {
                processCommand(command, out);
            }
        } catch (IOException e) {
            System.err.println("Error handling client request: " + e.getMessage());
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                System.err.println("Error closing client socket: " + e.getMessage());
            }
        }
    }

    private void processCommand(String command, PrintWriter out) {
        if (command.startsWith("index")) {
            String[] tokens = command.split("\\s+", 2);
            if (tokens.length == 2) {
                String[] data = tokens[1].split("\\s+");
                String filePath = data[0];
                String[] words = new String[data.length - 1];
                System.arraycopy(data, 1, words, 0, words.length);
                for (String word : words) {
                    store.addToLocalIndex(word, filePath, 1);
                }
                out.println("Indexing completed for file: " + filePath);
            } else {
                out.println("Invalid index command format");
            }
        } else if (command.startsWith("search")) {            
            out.println("Search command received");
        } else {
            out.println("Unrecognized command");
        }
    }
    public void join() throws InterruptedException {
        thread.join();
    } 
}
