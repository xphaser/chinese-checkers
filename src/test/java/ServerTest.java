

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


import org.junit.Test;

import server.Client;
import server.Game;

public class ServerTest {
    
    @Test
    public void testGame() throws IOException {
        ServerSocket listener = new ServerSocket(7734);

        Game game = new Game(2, listener);
        game.handleRequest(1, "MOVE 5 3 6 4");
        game.handleRequest(4, "MOVE 6 13 6 12");
        game.handleRequest(1, "MOVE 5 3 6 4");
        game.handleRequest(4, "MOVE 6 13 6 12");
        game.handleRequest(1, "MOVE 5 1 6 3");
        game.handleRequest(1, "MOVE 6 3 5 5");
        game.handleRequest(4, "MOVE 5 15 6 13");
        game.handleRequest(4, "MOVE 6 13 5 11");
        game.handleRequest(1, "MOVE 5 5 4 7");
        game.handleRequest(4, "MOVE 5 11 6 9");
        game.handleRequest(1, "SKIP");
        game.handleRequest(4, "SKIP");
        
        Socket socket = new Socket("localhost", 7734);
        Client client = new Client(socket, 1, game);
        client.start();
        client.send("Hello");
    }
    
}
