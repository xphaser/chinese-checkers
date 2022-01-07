package client;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import model.BasicBoard;
import model.Piece;

public class Controller {
    private BasicBoard board;
    private Client client;
    private Piece selectedPiece;
    
    @FXML
    private BorderPane pane;
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
        
        board = new BasicBoard();
        pane.setCenter(board);
        this.drawBoard();
    }
    
    private void drawBoard() {
        for(int i=0; i<board.getHeight(); i++) {
            double posY = i*40 + 40;
            
            for(int j=0; j<board.getWidth(); j++) {
                double posX = j*40 + 40;
                if(i%2 == 1) {
                    posX += 20;
                }
                System.out.println(board.getHeight());
                System.out.println(i +";" + j);
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
        switch(piece.getState()) {
        case 0:
            piece.setFill(Color.LIGHTGRAY);
            break;  
        case 1:
            piece.setFill(Constants.COLOR_RED);
            break;     
        case 2:
            piece.setFill(Constants.COLOR_YELLOW);
            break;
        case 3:
            piece.setFill(Constants.COLOR_BLACK);
            break;
        case 4:
            piece.setFill(Constants.COLOR_WHITE);
            break;
        case 5:
            piece.setFill(Constants.COLOR_BLUE);
            break;
        case 6:
            piece.setFill(Constants.COLOR_GREEN);
            break;
        default:
            piece.setFill(Color.TRANSPARENT);
        }
    }
    
    private void pieceClicked(Piece piece) {
        if(piece.getState() > 0) {
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
        else if(piece.getState() == 0 && selectedPiece!=null) {
            client.post("MOVE " + selectedPiece.getX() + " " + selectedPiece.getY() + " " + piece.getX() + " " + piece.getY());
            selectedPiece.setSelected(false);
            selectedPiece = null;
        }
    }
    
    public void movePiece(int oldX, int oldY, int newX, int newY) {
        board.setPiece(newX, newY, board.getPiece(oldX, oldY).getState());
        board.setPiece(oldX, oldY, 0);
        drawPiece(board.getPiece(oldX, oldY));
        drawPiece(board.getPiece(newX, newY));
    }
}
