package csc435.app;

public class FileRetrievalServer {
    public static void main(String[] args) {
        // pass the number of ports, the starting port, and the maximum number of connections
        if (args.length < 3) {
            System.out.println("Usage: java FileRetrievalServer <numPorts> <portStart> <maxConnections>");
            return;
        }

        int numPorts = Integer.parseInt(args[0]);
        int portStart = Integer.parseInt(args[1]);
        int maxConnections = Integer.parseInt(args[2]);

        IndexStore store = new IndexStore();
        ServerSideEngine engine = new ServerSideEngine(store);
        ServerAppInterface appInterface = new ServerAppInterface(engine);

        engine.initialize(numPorts, portStart, maxConnections); 
        appInterface.readCommands();
    }
}
