package model;

public class BoardController {
    private BasicBoard board;
    
    public BoardController(BasicBoard board) {
        this.board = board;
    }
    
    public boolean isValid(int player, int oldX, int oldY, int newX, int newY) {
        return true;
    }
}
