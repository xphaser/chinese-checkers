package server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Client extends Thread {
    private Socket socket;
    private Scanner in;
    private PrintWriter out;
    
    Client(Socket socket) {
        this.socket = socket;
        
        try {
            in = new Scanner(socket.getInputStream());
            out = new PrintWriter(socket.getOutputStream(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void run() {
        String req;
        
        while(true) {
            try {
                req = in.nextLine();
            }
            catch(NoSuchElementException e) {
                continue;
            }
            
            System.out.println("Client request: " + req);
            
             if(req.equals("TEST")) {
                out.println("OK");
            }
        }
    }
}
