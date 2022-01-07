package model;

import javafx.scene.Group;

public abstract class AbstractBoard extends Group {
    protected final int width;
    protected final int height;
    protected Piece pieces[][];
    
    public AbstractBoard(int width, int height) {
        this.width = width;
        this.height = height;
        this.init();
    }
    
    public int getWidth() {
        return this.width;
    }
    
    public int getHeight() {
        return this.height;
    }
    
    public Piece getPiece(int x, int y) {
        return pieces[x][y];
    }
    
    public void setPiece(int x, int y, int state) {
        pieces[x][y].setState(state);
    }
    
    private void init() {
        pieces = new Piece[width][height];
        
        for(int i=0; i<width; i++) {
            for(int j=0; j<height; j++) {
                pieces[i][j] = new Piece(i, j);
            }
        }
    }
    
}
