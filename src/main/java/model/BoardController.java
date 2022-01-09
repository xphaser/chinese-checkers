package model;

public class BoardController {
    private BasicBoard board;
    private boolean jumping = false;
    private Piece currentPiece;
    
    public BoardController(BasicBoard board) {
        this.board = board;
    }
    
    public boolean isValid(int player, int oldX, int oldY, int newX, int newY) {
        if(board.getPiece(newX, newY).getState() != 0) {
            return false;
        }
        if(board.getPiece(oldX, oldY).getState() != player) {
            return false;
        }
        
        return true;
    }
    
    public boolean isValidMove(int player, int oldX, int oldY, int newX, int newY) {
        
        if(this.jumping) {
            return false;
        }
        
        if(newY == oldY) {
            if(newX - oldX == 1 || newX - oldX == -1) {
              return true;
            }
        }

        if (newY - oldY == -1 || newY - oldY == 1) {
            if(oldY % 2 == 0) {
                if (newX - oldX == -1 || newX - oldX == 0) {
                    return true;
                }
            }
            if(oldY % 2 == 1) { 
                if(newX - oldX == 0 || newX - oldX == 1) {
                    return true;
                }
            }
        }

        return false;
    }
    
    public boolean isValidJump(int player, int oldX, int oldY, int newX, int newY) {
        
        if(jumping && currentPiece != board.getPiece(oldX, oldY)) {
            return false;
        }
        
        if(newY == oldY) {
            if(newX - oldX == 2 && board.getPiece(oldX + 1, oldY).getState()>0) {
                return true;
            }
            if(newX - oldX == -2 && board.getPiece(oldX - 1, oldY).getState()>0) {
                return true;
            }
        }
        
        if(newY - oldY == 2) {
            if(oldY % 2 == 0) {
                if(newX - oldX == -1 && board.getPiece(oldX - 1, oldY + 1).getState()>0) {
                    return true;
                }
                if(newX - oldX == 1 && board.getPiece(oldX, oldY + 1).getState()>0) {
                    return true;
                }
            }
            else {
                if(newX - oldX == -1 && board.getPiece(oldX, oldY + 1).getState()>0) {
                    return true;
                }
                if(newX - oldX == 1 && board.getPiece(oldX + 1, oldY + 1).getState()>0) {
                    return true;
                }
            }
        }
        
        if(newY - oldY == -2) {
            if(oldY % 2 == 0) {
                if(newX - oldX == -1 && board.getPiece(oldX - 1, oldY - 1).getState()>0) {
                    return true;
                }
                if(newX - oldX == 1 && board.getPiece(oldX, oldY - 1).getState()>0) {
                    return true;
                }
            }
            else {
                if(newX - oldX == -1 && board.getPiece(oldX, oldY - 1).getState()>0) {
                    return true;
                }
                if(newX - oldX == 1 && board.getPiece(oldX + 1, oldY - 1).getState()>0) {
                    return true;
                }
            }
        }
        
        return false;
    }

    public boolean isJumpPossible(int oldX, int oldY, int newX, int newY) {
        if(board.getPiece(newX-2, newY).getState() == 0 && board.getPiece(newX-1, newY).getState() > 0 && !(newX-2 == oldX && newY == oldY)) {
            return true;
        }
        if(board.getPiece(newX+2, newY).getState() == 0 && board.getPiece(newX+1, newY).getState() > 0 && !(newX+2 == oldX && newY == oldY)) {
            return true;
        }
        
        if(newY % 2 == 0) {
            //top left
            if(board.getPiece(newX-1, newY-2).getState() == 0 && board.getPiece(newX-1, newY-1).getState() > 0 && !(newX-1 == oldX && newY-2 == oldY)) {
                return true;
            }
            //top right
            if(board.getPiece(newX+1, newY-2).getState() == 0 && board.getPiece(newX, newY-1).getState() > 0 && !(newX+1 == oldX && newY-2 == oldY)) {
                return true;
            }
            //bottom left
            if(board.getPiece(newX-1, newY+2).getState() == 0 && board.getPiece(newX-1, newY+1).getState() > 0 && !(newX-1 == oldX && newY+2 == oldY)) {
                return true;
            }
            //bottom right
            if(board.getPiece(newX+1, newY+2).getState() == 0 && board.getPiece(newX, newY+1).getState() > 0 && !(newX+1 == oldX && newY+2 == oldY)) {
                return true;
            }
        }
        else {
            //top left
            if(board.getPiece(newX-1, newY-2).getState() == 0 && board.getPiece(newX, newY-1).getState() > 0 && !(newX-1 == oldX && newY-2 == oldY)) {
                return true;
            }
            //top right
            if(board.getPiece(newX+1, newY-2).getState() == 0 && board.getPiece(newX+1, newY-1).getState() > 0 && !(newX+1 == oldX && newY-2 == oldY)) {
                return true;
            }
            //bottom left
            if(board.getPiece(newX-1, newY+2).getState() == 0 && board.getPiece(newX, newY+1).getState() > 0 && !(newX-1 == oldX && newY+2 == oldY)) {
                return true;
            }
            //bottom right
            if(board.getPiece(newX+1, newY+2).getState() == 0 && board.getPiece(newX+1, newY+1).getState() > 0 && !(newX+1 == oldX && newY+2 == oldY)) {
                return true;
            }
        }
        
        return false;
    }
    
    public void isJumping(boolean jumping) {
        this.jumping = jumping;
    }
    
    public void setCurrentPiece(Piece piece) {
        this.currentPiece = piece;
    }
}
