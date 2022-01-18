package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private static final int port = 3000;
    
    public void start(int playersNum) throws IOException {
        try (ServerSocket listener = new ServerSocket(port)) {
            System.out.println("Server listening on port " + port);   
  
            new Game(playersNum, listener).start();
        }
    }
}
