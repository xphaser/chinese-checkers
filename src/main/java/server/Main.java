package server;
import java.io.IOException;
import server.Server;

public class Main {
    public static void main(String[] args) {
        if(args.length < 1)
            System.exit(1);
        
        int playersNum = Integer.parseInt(args[0]);
        
        Server server = new Server();
        
        try {
            server.start(playersNum);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
