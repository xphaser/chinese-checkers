package client;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;
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
                client.connect("localhost", 3000);
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
            return new RadialGradient(-135, 0.5, 0.5, 0.5, 0.5, true, CycleMethod.NO_CYCLE, new Stop(0, Color.WHITE), new Stop(1, Color.DARKGREY));
        case 2:
            return new RadialGradient(-135, 0.5, 0.5, 0.5, 0.5, true, CycleMethod.NO_CYCLE, new Stop(0, Color.WHITE), new Stop(1, Color.GOLD));
        case 3:
            return new RadialGradient(-135, 0.5, 0.5, 0.5, 0.5, true, CycleMethod.NO_CYCLE, new Stop(0, Color.WHITE), new Stop(1, Color.RED));
        case 4:
            return new RadialGradient(-135, 0.5, 0.5, 0.5, 0.5, true, CycleMethod.NO_CYCLE, new Stop(0, Color.LIGHTGREY), new Stop(1, Color.BLACK));
        case 5:
            return new RadialGradient(-135, 0.5, 0.5, 0.5, 0.5, true, CycleMethod.NO_CYCLE, new Stop(0, Color.WHITE), new Stop(1, Color.BLUE));
        case 6:
            return new RadialGradient(-135, 0.5, 0.5, 0.5, 0.5, true, CycleMethod.NO_CYCLE, new Stop(0, Color.WHITE), new Stop(1, Color.GREEN));
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
        if(pane!=null) pane.setCenter(board);
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
    
    public void showWinner(int playerId) {
        Alert alert = new Alert(AlertType.INFORMATION);
        
        if(this.playerId == playerId) {
            alert.setTitle("Victory!");
            alert.setHeaderText("Victory!");
            alert.setContentText("Congratulations! You have won!");
        }
        else {
            alert.setTitle("Defeat!");
            alert.setHeaderText("Defeat!");
            alert.setContentText("One of your opponents has won...");
        }
        
        alert.showAndWait();
    }
}
