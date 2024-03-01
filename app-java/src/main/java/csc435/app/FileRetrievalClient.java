package csc435.app;

import java.io.IOException;

public class FileRetrievalClient
{
    public static void main(String[] args) throws IOException
    {
        IndexStore store = new IndexStore();
        int threadNum = 1;
        ClientSideEngine engine = new ClientSideEngine(store,threadNum);
        ClientAppInterface appInterface = new ClientAppInterface(engine);
        
        // read commands from the user
        appInterface.readCommands();
    }
}
