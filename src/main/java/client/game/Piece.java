package client.game;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Piece extends Circle {
    private final int x;
    private final int y;
    private int state = 0;
    
    public Piece(int x, int y) {
        this.x = x;
        this.y = y;
        
        this.setOnMouseEntered(e -> {
            if(this.state >= 0) {
                this.setStroke(Color.GREY);
                this.setStrokeWidth(2);
            }
        });
        this.setOnMouseExited(e -> {
            this.setStroke(Color.TRANSPARENT);
        });
    }

    public int getX() {
        return this.x;
    }
    
    public int getY() {
        return this.y;
    }
    
    public int getState() {
        return this.state;
    }
    
    public void setState(int state) {
        this.state = state;
    }
    
}
