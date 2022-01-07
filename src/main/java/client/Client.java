package client;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import javafx.application.Platform;

public class Client {
    private Socket socket;
    private Scanner in;
    private PrintWriter out;
    private Controller controller;
    
    public Client(Controller controller) {
        this.controller = controller;
    }
    
    public void connect(String address, int port) throws UnknownHostException, IOException {
        socket = new Socket(address, port);
        in = new Scanner(socket.getInputStream());
        out = new PrintWriter(socket.getOutputStream(), true);
    }
    
    public void run() {
        String res;
        while(true) {
            try {
                res = in.nextLine();
            }
            catch(NoSuchElementException e) {
                continue;
            }
            
            String tokens[] = res.split(" ");
            if(tokens[0].equals("MOVE")) {
                int oldX = Integer.parseInt(tokens[1]);
                int oldY = Integer.parseInt(tokens[2]);
                int newX = Integer.parseInt(tokens[3]);
                int newY = Integer.parseInt(tokens[4]);
                
                Platform.runLater(() -> controller.movePiece(oldX, oldY, newX, newY));
            }
        }
    }
    
    public void post(String req) {
        out.println(req);
    }
}
