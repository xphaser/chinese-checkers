

import org.junit.Test;

import model.BasicBoard;
import model.BoardController;

import org.junit.Assert;

public class BoardControllerTest {
    
    
    @Test
    public void testValid() {
        BasicBoard board = new BasicBoard();
        BoardController controller = new BoardController(board);
        
        board.setPiece(5, 4, 1);
        board.setPiece(6, 4, 1);
        board.setPiece(6, 13, 1);
        Assert.assertFalse(controller.isValid(1, 5, 4, 6, 4)); //target field non-empty
        Assert.assertFalse(controller.isValid(2, 5, 4, 6, 5)); //another player's piece
        Assert.assertFalse(controller.isValid(1, 6, 13, 7, 12)); //move from opposite triangle outside
        Assert.assertTrue(controller.isValid(1, 6, 13, 6, 14)); //move inside opposite triangle
        Assert.assertTrue(controller.isValid(1, 5, 4, 4, 5)); //valid
    }
    
    @Test
    public void testMove() {
        BasicBoard board = new BasicBoard();
        BoardController controller = new BoardController(board);
        board.setPiece(6, 8, 1); //even row
        
        controller.isJumping(true);
        Assert.assertFalse(controller.isValidMove(1, 6, 8, 5, 8));
        controller.isJumping(false);
        
        Assert.assertTrue(controller.isValidMove(1, 6, 8, 5, 8)); //left
        Assert.assertTrue(controller.isValidMove(1, 6, 8, 7, 8)); //right
        Assert.assertTrue(controller.isValidMove(1, 6, 8, 5, 7)); //top left
        Assert.assertTrue(controller.isValidMove(1, 6, 8, 6, 7)); //top right
        Assert.assertTrue(controller.isValidMove(1, 6, 8, 5, 9)); //bottom left
        Assert.assertTrue(controller.isValidMove(1, 6, 8, 6, 9)); //bottom right
        
        board.setPiece(5, 7, 1); //odd row
        
        Assert.assertTrue(controller.isValidMove(1, 5, 7, 5, 6)); //top left
        Assert.assertTrue(controller.isValidMove(1, 5, 7, 6, 6)); //top right
        Assert.assertTrue(controller.isValidMove(1, 5, 7, 5, 8)); //bottom left
        Assert.assertTrue(controller.isValidMove(1, 5, 7, 6, 8)); //bottom right
        
        Assert.assertFalse(controller.isValidMove(1, 6, 8, 4, 8)); //move > 1 field
        Assert.assertFalse(controller.isValidMove(1, 5, 7, 4, 6)); 
        Assert.assertFalse(controller.isValidMove(1, 6, 8, 4, 7));
    }
    
    @Test
    public void testJump() {
        BasicBoard board = new BasicBoard();
        BoardController controller = new BoardController(board);
        board.setPiece(6, 8, 1); //even row
        
        controller.isJumping(true);
        Assert.assertFalse(controller.isValidJump(1, 6, 8, 4, 8));
        controller.isJumping(false);
        
        Assert.assertFalse(controller.isJumpPossible(0, 0, 6, 8));
        Assert.assertFalse(controller.isValidJump(1, 6, 8, 4, 8)); //left
        Assert.assertFalse(controller.isValidJump(1, 6, 8, 8, 8)); //right
        Assert.assertFalse(controller.isValidJump(1, 6, 8, 5, 6)); //top left
        Assert.assertFalse(controller.isValidJump(1, 6, 8, 7, 6)); //top right
        Assert.assertFalse(controller.isValidJump(1, 6, 8, 5, 10)); //bottom left
        Assert.assertFalse(controller.isValidJump(1, 6, 8, 7, 10)); //bottom right
        
        board.setPieces(new int[][]{{5,8}, {5,7}, {6,7}, {7,8}, {6,9}, {5,9}}, 1);
        Assert.assertTrue(controller.isValidJump(1, 6, 8, 4, 8)); //left
        Assert.assertTrue(controller.isValidJump(1, 6, 8, 8, 8)); //right
        Assert.assertTrue(controller.isValidJump(1, 6, 8, 5, 6)); //top left
        Assert.assertTrue(controller.isValidJump(1, 6, 8, 7, 6)); //top right
        Assert.assertTrue(controller.isValidJump(1, 6, 8, 5, 10)); //bottom left
        Assert.assertTrue(controller.isValidJump(1, 6, 8, 7, 10)); //bottom right
        
        Assert.assertTrue(controller.isJumpPossible(2, 2, 6, 8));
        board.setPiece(5, 8, 0);
        Assert.assertTrue(controller.isJumpPossible(2, 2, 6, 8));
        board.setPiece(7, 8, 0);
        Assert.assertTrue(controller.isJumpPossible(2, 2, 6, 8));
        board.setPiece(5, 7, 0);
        Assert.assertTrue(controller.isJumpPossible(2, 2, 6, 8));
        board.setPiece(6, 7, 0);
        Assert.assertTrue(controller.isJumpPossible(2, 2, 6, 8));
        board.setPiece(5, 9, 0);
        Assert.assertTrue(controller.isJumpPossible(2, 2, 6, 8));
        board.setPiece(6, 9, 0);
        board.setPiece(6, 8, 0);
        
        board.setPiece(5, 7, 1); //odd row
        Assert.assertFalse(controller.isJumpPossible(0, 0, 5, 7));//maybe
        Assert.assertFalse(controller.isValidJump(1, 5, 7, 4, 5)); //top left
        Assert.assertFalse(controller.isValidJump(1, 5, 7, 6, 5)); //top right
        Assert.assertFalse(controller.isValidJump(1, 5, 7, 4, 9)); //bottom left
        Assert.assertFalse(controller.isValidJump(1, 5, 7, 6, 9)); //bottom right
        
        board.setPieces(new int[][]{{5,6}, {6,6}, {6,8}, {5,8}}, 1);
        Assert.assertTrue(controller.isValidJump(1, 5, 7, 4, 5)); //top left
        Assert.assertTrue(controller.isValidJump(1, 5, 7, 6, 5)); //top right
        Assert.assertTrue(controller.isValidJump(1, 5, 7, 4, 9)); //bottom left
        Assert.assertTrue(controller.isValidJump(1, 5, 7, 6, 9)); //bottom right
        
        Assert.assertTrue(controller.isJumpPossible(2, 2, 5, 7));
        board.setPiece(5, 6, 0);
        Assert.assertTrue(controller.isJumpPossible(2, 2, 5, 7));
        board.setPiece(6, 6, 0);
        Assert.assertTrue(controller.isJumpPossible(2, 2, 5, 7));
        board.setPiece(5, 8, 0);
        Assert.assertTrue(controller.isJumpPossible(2, 2, 5, 7));
    }
    
    @Test
    public void testVictory() {
        BasicBoard board = new BasicBoard();
        BoardController controller = new BoardController(board);
        
        Assert.assertTrue(controller.findWinner() == 0);
        
        board.setPieces(board.getRedTriangle(), 6);
        Assert.assertTrue(controller.findWinner() == 6);
        
        board.setPieces(board.getYellowTriangle(), 5);
        Assert.assertTrue(controller.findWinner() == 5);
        
        board.setPieces(board.getWhiteTriangle(), 4);
        Assert.assertTrue(controller.findWinner() == 4);
        
        board.setPieces(board.getGreenTriangle(), 3);
        Assert.assertTrue(controller.findWinner() == 3);
        
        board.setPieces(board.getBlueTriangle(), 2);
        Assert.assertTrue(controller.findWinner() == 2);
        
        board.setPieces(board.getBlackTriangle(), 1);
        Assert.assertTrue(controller.findWinner() == 1);
    }
}