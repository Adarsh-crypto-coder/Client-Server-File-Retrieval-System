package csc435.app;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;

public class Dispatcher implements Runnable {
    private ServerSideEngine engine;
    private int port;
    private int maxConnections;
    private String address;
    private ExecutorService executor;
  
    public Dispatcher(ServerSideEngine engine, int port, int maxConnections, String address) {
      this.engine = engine;
      this.port = port;
      this.maxConnections = maxConnections;
      this.address = address;
    }
  
    @Override
    public void run() {
      try {
        ServerSocket server = new ServerSocket(port, maxConnections, InetAddress.getByName(address));
        System.out.println("Server running on port " + port + ", waiting for connections...");
        while (true) {
          Socket socket = server.accept();
          System.out.println("New connection from " + socket.getInetAddress() + ":" + socket.getPort());
          engine.spawnWorker(socket); // Forward connection to the engine for worker creation
        }
      } catch (IOException e) {
        System.err.println("Error creating server socket: " + e.getMessage());
      }
    }  
            
    public void shutdown() {
      if(executor != null)
      executor.shutdown();
    }
}
  