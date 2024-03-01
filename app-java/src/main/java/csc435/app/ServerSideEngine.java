package csc435.app;

import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ServerSideEngine {
    private IndexStore store;
    private List<Dispatcher> dispatchers;
    private List<Worker> connectedClients;
  
    public ServerSideEngine(IndexStore store) {
      this.store = store;
      dispatchers = new ArrayList<>();
      connectedClients = new ArrayList<>();
    }
  
    public void initialize(int numPorts, int portStart, int maxConnections) {
      for (int i = 0; i < numPorts; i++) {
        int port = portStart + i;
        Dispatcher dispatcher = new Dispatcher(this, port, maxConnections, "127.0.0.1"); 
        Thread dispatcherThread = new Thread(dispatcher);
        dispatchers.add(dispatcher);
        dispatcherThread.start();
      }
    }

    public void spawnWorker(Socket socket) {
    Worker worker = new Worker(store, socket);
    Thread workerThread = new Thread(worker);
    connectedClients.add(worker);
    workerThread.start();
    }  

    public List<String> list() {
        System.out.println("Number of connected clients: " + connectedClients.size());
        List<String> clientAddresses = new ArrayList<>();
        for (Worker worker : connectedClients) {
            clientAddresses.add(worker.getIpAddress());
        }
        return clientAddresses;
    }

    public void shutdown() {
      
      for (Dispatcher dispatcher : dispatchers) {
        dispatcher.shutdown();
      } 
        for (Worker worker : connectedClients) {
          try {
              worker.join();
          } catch (InterruptedException e) {
              System.err.println("Error waiting for worker thread to join: " + e.getMessage());
          }
      }
      System.exit(0);
  }
  
  }
