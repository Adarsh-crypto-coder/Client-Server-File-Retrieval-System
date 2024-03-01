package csc435.app;

import java.io.IOException;
import java.lang.System;
import java.util.Scanner;

public class ClientAppInterface {
    private ClientSideEngine engine;

    public ClientAppInterface(ClientSideEngine engine) {
        this.engine = engine;

        // TO-DO implement constructor
        // keep track of the connection with the client
    }

    public void readCommands() throws IOException {
        // TO-DO implement the read commands method
        Scanner sc = new Scanner(System.in);
        String command;
        
        while (true) {
            System.out.print("> ");
            
            // read from command line
            command = sc.nextLine();

            // if the command is quit, terminate the program       
            if (command.compareTo("quit") == 0) {
                engine.closeConnection();
                break;
            }

            // if the command begins with connect, connect to the given server
            if (command.length() >= 7 && command.substring(0, 7).compareTo("connect") == 0) {
                // TO-DO implement index operation
                // call the connect method from the server side engine
                String[] tokens = command.split(" ");
                if (tokens.length == 3) {
                    String server = tokens[1];
                    int port = Integer.parseInt(tokens[2]);
                    engine.openConnection(server, port);
                }else{
                    System.out.println("Invalid connect command");
                }
                continue;
            }
            
            // if the command begins with index, index the files from the specified directory
            if (command.length() >= 5 && command.substring(0, 5).compareTo("index") == 0) {
                String[] tokens = command.split(" ");
                if (tokens.length == 2) {
                    String datasetPath = tokens[1].trim();
                if (!datasetPath.isEmpty()) {
                  engine.indexFiles(datasetPath);
                } else {
                 System.out.println("Please provide the dataset path.");
            }
            } else {
            System.out.println("Invalid index command format. Usage: index <dataset_path>");
        }
        continue;
    }
        // if the command begins with search, search for files that matches the query
            if (command.length() >= 6 && command.substring(0, 6).compareTo("search") == 0) {
                String query = command.substring(6).trim(); // Extract the query from the command
            if (!query.isEmpty()) {
                engine.searchFiles(query);
            } else {
                System.out.println("Please provide a search query.");
            }
            continue;
            }
        }

        sc.close();
    }
}
