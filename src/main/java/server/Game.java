package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;

import model.BasicBoard;
import model.BoardController;

public class Game {    
    private ServerSocket listener;
    private BasicBoard board;
    private BoardController controller;
    private List<Client> players;
    private int playersNum;
    private int turn = 0;

    Game(int playersNum, ServerSocket socket) throws IOException {
        this.playersNum = playersNum;
        this.listener = socket;
        this.addPlayers();
        this.board = new BasicBoard();
        this.controller = new BoardController(board);
        this.start();
    }
    
    private void addPlayers() throws IOException {
        players = new ArrayList<>();
        int i = 0;
        
        do {
            i++;
            players.add(new Client(listener.accept(), i, this));
            System.out.println("Player " + i + " connected");
        } while (i < playersNum);
    }
    
    private void start() {
        System.out.println("Starting");
        for(Client player : players) {
            player.send("START " + player.getPlayerId());
            player.start();
        }
        nextTurn();
    }
    
    private void nextTurn() {
        turn = turn % playersNum + 1;
        sendAll("TURN " + turn);
    }
    
    public void sendAll(String message) {
        for(Client player: players) {
            player.send(message);
        }
    }
    
    public void handleRequest(int playerId, String req) {
        if(playerId != turn) {
            return;
        }
        
        String tokens[] = req.split(" ");

        if(tokens[0].equals("MOVE")) {
            
            int oldX = Integer.parseInt(tokens[1]);
            int oldY = Integer.parseInt(tokens[2]);
            int newX = Integer.parseInt(tokens[3]);
            int newY = Integer.parseInt(tokens[4]);
            
            if(!controller.isValid(playerId, oldX, oldY, newX, newY)) {
                return;
            }
           
            if(controller.isValidMove(playerId, oldX, oldY, newX, newY)) {
                board.setPiece(oldX, oldY, 0);
                board.setPiece(newX, newY, playerId);
                sendAll("MOVE " + oldX + " " + oldY + " " + newX + " " + newY);
                this.nextTurn();
                return;
            }
           
            if(controller.isValidJump(playerId, oldX, oldY, newX, newY)) {
                board.setPiece(oldX, oldY, 0);
                board.setPiece(newX, newY, playerId);
                sendAll("MOVE " + oldX + " " + oldY + " " + newX + " " + newY);
                
                if(!controller.isJumpPossible(oldX, oldY, newX, newY)) {
                    controller.isJumping(false);
                    this.nextTurn();
                }
                else {
                    controller.isJumping(true);
                    controller.setCurrentPiece(board.getPiece(newX, newY));
                }
            }
            
        }
        else if(tokens[0].equals("SKIP")) {
            controller.isJumping(false);
            this.nextTurn();
        }
    }
    
}
