package csc435.app;

import java.lang.System;
import java.util.List;
import java.util.Scanner;

public class ServerAppInterface {
    private ServerSideEngine engine;

    public ServerAppInterface(ServerSideEngine engine) {
        this.engine = engine;
    }

    public void readCommands() {
        Scanner sc = new Scanner(System.in);
        String command;
        
        while (true) {
            System.out.print("> ");
            
            // Read from command line
            command = sc.nextLine();
    
            // If the command is quit, terminate the program
            if (command.compareTo("quit") == 0) {
                engine.shutdown();
                break;
            }
    
            // If the command begins with list, list all the connected clients
            if (command.length() >= 4 && command.substring(0, 4).equals("list")) {
                // Call the list method from the server to retrieve the clients information
                List<String> clients = engine.list();
                // Print the clients information
                for (int i = 0; i < clients.size(); i++) {
                    System.out.println("Client " + (i + 1) + ": " + clients.get(i));
                }
                continue;
            }
    
            System.out.println("Unrecognized command!");
        }
    
        sc.close();
    }
    
}
