package client;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Client {
    private Socket socket;
    private Scanner in;
    private PrintWriter out;
    
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
            
            System.out.println("Server responded: " + res);
        }
    }
    
    public void post(String req) {
        out.println(req);
    }
}
