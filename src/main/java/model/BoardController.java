package model;

public class BoardController {
    private BasicBoard board;
    
    public BoardController(BasicBoard board) {
        this.board = board;
    }
    
    public boolean isValid(int player, int oldX, int oldY, int newX, int newY) {
        if(newY == oldY) {
            if(newX - oldX == 1) {
              return true;
            }
            if(newX - oldX == -1) {
              return true;
            }
          }

        if (newY - oldY == -1 || newY - oldY == 1) {
            if(oldY % 2 == 0) {
                if (newX - oldX == -1) {
                    return true;
                }
                if (newX - oldX == 0) {
                    return true;
                }
            }
            if(oldY % 2 == 1) { 
                if(newX - oldX == 0) {
                    return true;
                }
                if(newX - oldX == 1) {
                    return true;
                }
            }
        }

        return false;
    }
    
}
