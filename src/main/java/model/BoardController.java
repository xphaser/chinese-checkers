package model;

public class BoardController {
    private BasicBoard board;
    private boolean jumping = false;
    private Piece currentPiece = null;
    
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
        
        int[][] oppositeTriangle;
        
        switch(player) {
        case 1:
            oppositeTriangle = board.getGreenTriangle();
            break;
        case 2:
            oppositeTriangle = board.getWhiteTriangle();
            break;
        case 3:
            oppositeTriangle = board.getYellowTriangle();
            break;
        case 4:
            oppositeTriangle = board.getRedTriangle();
            break;
        case 5:
            oppositeTriangle = board.getBlackTriangle();
            break;
        case 6:
            oppositeTriangle = board.getBlueTriangle();
            break;
        default:
            return false;
        }
        
        for(int i=0; i<oppositeTriangle.length; i++) {
            if(oldX == oppositeTriangle[i][0] && oldY == oppositeTriangle[i][1]) {
                for(int j=0; j<oppositeTriangle.length; j++) {
                    if(newX == oppositeTriangle[j][0] && newY == oppositeTriangle[j][1]) {
                        return true;
                    }
                }
                return false;
            }
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
            else { 
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
        if(newX-2 >= 0 
                && board.getPiece(newX-2, newY).getState() == 0 && board.getPiece(newX-1, newY).getState() > 0
                && !(newX-2 == oldX && newY == oldY)) 
            return true;
        
        if(newX+2 < board.getWidth() 
                && board.getPiece(newX+2, newY).getState() == 0 && board.getPiece(newX+1, newY).getState() > 0 
                && !(newX+2 == oldX && newY == oldY))
            return true;
    
        
        if(newY % 2 == 0) {
            //top left
            if(newX-1 >= 0 && newY-2 >= 0 
                    && board.getPiece(newX-1, newY-2).getState() == 0 && board.getPiece(newX-1, newY-1).getState() > 0
                    && !(newX-1 == oldX && newY-2 == oldY))
                return true;

            //top right
            if(newX+1 < board.getWidth() && newY-2 >= 0 
                    && board.getPiece(newX+1, newY-2).getState() == 0 && board.getPiece(newX, newY-1).getState() > 0 
                    && !(newX+1 == oldX && newY-2 == oldY)) {
                return true;
            }
            //bottom left
            if(newX-1 >= 0 && newY+2 < board.getHeight() 
                    && board.getPiece(newX-1, newY+2).getState() == 0 && board.getPiece(newX-1, newY+1).getState() > 0 
                    && !(newX-1 == oldX && newY+2 == oldY))
                return true;

            //bottom right
            if(newX+1 < board.getWidth() && newY+2 < board.getHeight() 
                    && board.getPiece(newX+1, newY+2).getState() == 0 && board.getPiece(newX, newY+1).getState() > 0 
                    && !(newX+1 == oldX && newY+2 == oldY))
                return true;
        }
        else {
            //top left
            if(newX-1 >= 0 && newY-2 >= 0 
                    && board.getPiece(newX-1, newY-2).getState() == 0 && board.getPiece(newX, newY-1).getState() > 0 
                    && !(newX-1 == oldX && newY-2 == oldY))
                return true;
    
            //top right
            if(newX+1 < board.getHeight() && newY-2 >= 0 
                    && board.getPiece(newX+1, newY-2).getState() == 0 && board.getPiece(newX+1, newY-1).getState() > 0 
                    && !(newX+1 == oldX && newY-2 == oldY))
                return true;
            
            //bottom left
            if(newX-1 >= 0 && newY+2 < board.getHeight() 
                    && board.getPiece(newX-1, newY+2).getState() == 0 && board.getPiece(newX, newY+1).getState() > 0 
                    && !(newX-1 == oldX && newY+2 == oldY))
                return true;

            //bottom right
            if(newX+1 < board.getWidth() && newY+2 < board.getHeight()
                    && board.getPiece(newX+1, newY+2).getState() == 0 && board.getPiece(newX+1, newY+1).getState() > 0 
                    && !(newX+1 == oldX && newY+2 == oldY))
                return true;
        }
        
        return false;
    }
    
    public int findWinner() {
        if(redVictory()) return 1;
        if(blackVictory()) return 2;
        if(blueVictory()) return 3;
        if(greenVictory()) return 4;
        if(whiteVictory()) return 5;
        if(yellowVictory()) return 6;
        return 0;
    }
    
    private boolean redVictory() {
        int[][] oppositeTriangle = board.getGreenTriangle();
        int counter = 0;
        
        for(int i=0; i<oppositeTriangle.length; i++) {
            Piece piece = board.getPiece(oppositeTriangle[i][0], oppositeTriangle[i][1]);
            if(piece != null && piece.getState() == 1) {
                counter++;
            }
        }
        
        if(counter == 10) {
            return true;
        }
        return false;
    }
    
    private boolean blackVictory() {
        int[][] oppositeTriangle = board.getWhiteTriangle();
        int counter = 0;
        
        for(int i=0; i<oppositeTriangle.length; i++) {
            Piece piece = board.getPiece(oppositeTriangle[i][0], oppositeTriangle[i][1]);
            if(piece != null && piece.getState() == 2) {
                counter++;
            }
        }
        
        if(counter == 10) {
            return true;
        }
        return false;
    }
    
    private boolean blueVictory() {
        int[][] oppositeTriangle = board.getYellowTriangle();
        int counter = 0;
        
        for(int i=0; i<oppositeTriangle.length; i++) {
            Piece piece = board.getPiece(oppositeTriangle[i][0], oppositeTriangle[i][1]);
            if(piece != null && piece.getState() == 3) {
                counter++;
            }
        }
        
        if(counter == 10) {
            return true;
        }
        return false;
    }
    
    private boolean greenVictory() {
        int[][] oppositeTriangle = board.getRedTriangle();
        int counter = 0;
        
        for(int i=0; i<oppositeTriangle.length; i++) {
            Piece piece = board.getPiece(oppositeTriangle[i][0], oppositeTriangle[i][1]);
            if(piece != null && piece.getState() == 4) {
                counter++;
            }
        }
        
        if(counter == 10) {
            return true;
        }
        return false;
    }
    
    private boolean whiteVictory() {
        int[][] oppositeTriangle = board.getBlackTriangle();
        int counter = 0;
        
        for(int i=0; i<oppositeTriangle.length; i++) {
            Piece piece = board.getPiece(oppositeTriangle[i][0], oppositeTriangle[i][1]);
            if(piece != null && piece.getState() == 5) {
                counter++;
            }
        }
        
        if(counter == 10) {
            return true;
        }
        return false;
    }
    
    private boolean yellowVictory() {
        int[][] oppositeTriangle = board.getBlueTriangle();
        int counter = 0;
        
        for(int i=0; i<oppositeTriangle.length; i++) {
            Piece piece = board.getPiece(oppositeTriangle[i][0], oppositeTriangle[i][1]);
            if(piece != null && piece.getState() == 6) {
                counter++;
            }
        }
        
        if(counter == 10) {
            return true;
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
