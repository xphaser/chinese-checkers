package client.game;

public class BasicBoard extends AbstractBoard {
    
    private static final int[][] nullPieces = {
            {0, 7, 0, 7, 0, 8, 0, 8, 12,0, 12,0, 11,0, 11,0, 11,0, 12,12,0, 8, 0, 8, 0, 7, 0, 7 },
            {0, 0, 1, 1, 2, 2, 3, 3, 5, 6, 6, 7, 7, 8, 8, 9, 9, 10,10,11,13,13,14,14,15,15,16,16},
            {6, 6, 5, 6, 5, 5, 4, 5, 1, 1, 1, 1, 2, 2, 2, 1, 2, 1, 1, 1, 4, 5, 5, 5, 5, 6, 6, 6 }
    };
    private static final int[][] redPieces = {
            {6,5,5,4},
            {0,1,2,3},
            {1,2,3,4}
    };
    private static final int[][] yellowPieces = {
            {0,0,1,1},
            {4,5,6,7},
            {4,3,2,1}
    };
    private static final int[][] blackPieces = {
            {9,9,10,10},
            {4,5,6,7},
            {4,3,2,1}
    };
    private static final int[][] whitePieces = {
            {1,1,0,0},
            {9,10,11,12},
            {1,2,3,4}
    };
    private static final int[][] bluePieces = {
            {10,10,9,9},
            {9,10,11,12},
            {1,2,3,4}
    };
    private static final int[][] greenPieces = {
            {4,5,5,6},
            {13,14,15,16},
            {4,3,2,1}
    };
    
    public BasicBoard(int width, int height) {
        super(width, height);
        this.init();
    }
    
    private void init() {
        this.setPieces(nullPieces, -1);
        this.setPieces(redPieces, 1);
        this.setPieces(yellowPieces, 2);
        this.setPieces(blackPieces, 3);
        this.setPieces(whitePieces, 4);
        this.setPieces(bluePieces, 5);
        this.setPieces(greenPieces, 6);
    }
    
    private void setPieces(int[][] test, int state) {
        for(int i=0; i < test[0].length; i++) {
            for(int j=0; j < test[2][i]; j++) {
                pieces[test[0][i]+j][test[1][i]].setState(state);
            }
        }
    }
    
}
