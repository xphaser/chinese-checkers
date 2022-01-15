package client;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.RadialGradient;
import javafx.scene.shape.Circle;
import model.BasicBoard;
import model.Piece;

public class Controller {
    private BasicBoard board;
    private Client client;
    private Piece selectedPiece;
    private int playerId;
    
    @FXML
    private BorderPane pane;
    @FXML
    private Label label;
    @FXML
    private Button buttonSkip;
    @FXML
    private Circle turnPiece;
    
    @FXML
    public void initialize() {
        client = new Client(this);
        
        new Thread(() -> {
            try {
                client.connect(Constants.SERVER_HOST, Constants.SERVER_PORT);
                client.run();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }
    
    private void drawBoard() {
        for(int i=0; i<board.getHeight(); i++) {
            double posY = i*40 + 40;
            
            for(int j=0; j<board.getWidth(); j++) {
                double posX = j*40 + 40;
                if(i%2 == 1) {
                    posX += 20;
                }
                Piece piece = board.getPiece(j, i);
                
                piece.setCenterX(posX);
                piece.setCenterY(posY);
                piece.setRadius(17);
                piece.setOnMouseClicked(e -> pieceClicked(piece));
                drawPiece(piece);

                board.getChildren().add(piece);
            }
        }
    }
    
    private void drawPiece(Piece piece) {
        int id = piece.getState();
        if(id == -1) {
            piece.setFill(Color.TRANSPARENT);
        }
        else if(id == 0) {
            piece.setFill(Color.LIGHTGREY);
        }
        else {
            piece.setFill(getColor(piece.getState()));
        }
    }
    
    private RadialGradient getColor(int id) throws IllegalArgumentException {
        switch(id) {
        case 1:
            return Constants.COLOR_RED;
        case 2:
            return Constants.COLOR_BLACK;
        case 3:
            return Constants.COLOR_BLUE;
        case 4:
            return Constants.COLOR_GREEN;
        case 5:
            return Constants.COLOR_WHITE;
        case 6:
            return Constants.COLOR_YELLOW;
        default:
            throw new IllegalArgumentException();
        }
    }
    
    private void pieceClicked(Piece piece) {
        if(piece.getState() == playerId) {
            if(!piece.isSelected()) {
                if(selectedPiece != null) {
                    selectedPiece.setSelected(false);
                }
                piece.setSelected(true);    
                selectedPiece = piece;
            }
            else {
                piece.setSelected(false);
                selectedPiece = null;
            }
        }
        else if(piece.getState() == 0 && selectedPiece != null) {
            client.post("MOVE " + selectedPiece.getX() + " " + selectedPiece.getY() + " " + piece.getX() + " " + piece.getY());
            selectedPiece.setSelected(false);
            selectedPiece = null;
        }
    }
   
    @FXML
    private void skipTurn() {
        client.post("SKIP");
        
        if(selectedPiece!= null) {
            selectedPiece.setSelected(false);
            selectedPiece = null;
        }
    }
    
    public void initBoard(int playersNum) {
        board = new BasicBoard();
        pane.setCenter(board);
        board.init(playersNum);
        drawBoard();
    }
    
    public void movePiece(int oldX, int oldY, int newX, int newY) {
        board.setPiece(newX, newY, board.getPiece(oldX, oldY).getState());
        board.setPiece(oldX, oldY, 0);
        drawPiece(board.getPiece(oldX, oldY));
        drawPiece(board.getPiece(newX, newY));
    }
    
    public void start(int playerId) {
        this.playerId = playerId;
    }
    
    public void nextTurn(int playerId) {
        if(this.playerId == playerId) {
            label.setText("YOUR TURN");
        }
        else {
            label.setText("ENEMY TURN");
        }
        
        turnPiece.setFill(getColor(playerId));
    }
}
