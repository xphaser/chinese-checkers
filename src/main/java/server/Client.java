package server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.NoSuchElementException;
import java.util.Scanner;

import model.BoardController;

public class Client extends Thread {
    private Socket socket;
    private Scanner in;
    private PrintWriter out;
    private Game game;
    private int id;
    
    Client(Socket socket, int id, Game game) {
        this.socket = socket;
        this.id = id;
        this.game = game;
        
        try {
            in = new Scanner(socket.getInputStream());
            out = new PrintWriter(socket.getOutputStream(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public int getPlayerId() {
        return this.id;
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
            
            System.out.println(this.id + ": " + req);
            game.handleRequest(this.id, req);
        }
    }
    
    public void send(String message) {
        out.println(message);
    }
}
