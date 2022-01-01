package server;
import java.io.IOException;
import server.Server;

public class Main {
    public static void main(String[] args) {
        Server server = new Server();
        
        try {
            server.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
