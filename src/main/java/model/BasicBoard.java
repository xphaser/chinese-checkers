package model;

public class BasicBoard extends AbstractBoard {
    
    private static final int[][] nullPieces = {
            {0,0}, {1,0}, {2,0}, {3,0}, {4,0}, {5,0}, {7,0}, {8,0}, {9,0}, {10,0}, {11,0}, {12,0}, 
            {0,1}, {1,1}, {2,1}, {3,1}, {4,1}, {7,1}, {8,1}, {9,1}, {10,1}, {11,1}, {12,1}, 
            {0,2}, {1,2}, {2,2}, {3,2}, {4,2}, {8,2}, {9,2}, {10,2}, {11,2}, {12,2}, 
            {0,3}, {1,3}, {2,3}, {3,3}, {8,3}, {9,3}, {10,3}, {11,3}, {12,3},
            {12,5}, {0,6}, {12,6},{0,7}, {11,7}, {12,7}, {0,8}, {1,8}, {11,8}, {12,8}, {0,9}, {11,9}, {12,9}, 
            {0,10}, {12,10}, {12,11}, {0,13}, {1,13}, {2,13}, {3,13}, {8,13}, {9,13}, {10,13}, {11,13}, {12,13}, 
            {0,14}, {1,14}, {2,14}, {3,14}, {4,14}, {8,14}, {9,14}, {10,14}, {11,14}, {12,14}, 
            {0,15}, {1,15}, {2,15}, {3,15}, {4,15}, {7,15}, {8,15}, {9,15}, {10,15}, {11,15}, {12,15},
            {0,16}, {1,16}, {2,16}, {3,16}, {4,16}, {5,16}, {7,16}, {8,16}, {9,16}, {10,16}, {11,16}, {12,16}
    };
    private static final int[][] redPieces = {
            {6,0}, {5,1}, {6,1}, {5,2}, {6,2}, {7,2}, {4,3}, {5,3}, {6,3}, {7,3}
    };
    private static final int[][] blackPieces = {
            {9,4}, {10,4}, {11,4}, {12,4}, {9,5}, {10,5}, {11,5}, {10,6}, {11,6}, {10,7}
    };
    private static final int[][] bluePieces = {
            {10,9}, {10,10}, {11,10}, {9,11}, {10,11}, {11,11}, {9,12}, {10,12}, {11,12}, {12,12}
    };
    private static final int[][] greenPieces = {
            {4,13}, {5,13}, {6,13}, {7,13}, {5,14}, {6,14}, {7,14}, {5,15}, {6,15}, {6,16}
    };
    private static final int[][] whitePieces = {
            {1,9}, {1,10}, {2,10}, {0,11}, {1,11}, {2,11}, {0,12}, {1,12}, {2,12}, {3,12}
    };
    private static final int[][] yellowPieces = {
            {0,4}, {1,4}, {2,4}, {3,4}, {0,5}, {1,5}, {2,5}, {1,6}, {2,6}, {1,7}
    };


    private static final int width = 13;
    private static final int height = 17;

    public BasicBoard() {
        super(width, height);
        this.setPieces(nullPieces, -1);
    }

    public void init(int playersNum) {
        if(playersNum == 2) {
            this.setPieces(redPieces, 1);
            this.setPieces(greenPieces, 4);
        }
        else if(playersNum == 3) {
            this.setPieces(redPieces, 1);
            this.setPieces(bluePieces, 3);
            this.setPieces(whitePieces, 5);


        }
        else if(playersNum == 4) {
            this.setPieces(blackPieces, 2);
            this.setPieces(bluePieces, 3);
            this.setPieces(whitePieces, 5);
            this.setPieces(yellowPieces, 6);
        }
        else if(playersNum == 6) {
            this.setPieces(redPieces, 1);
            this.setPieces(blackPieces, 2);
            this.setPieces(bluePieces, 3);
            this.setPieces(greenPieces, 4);
            this.setPieces(whitePieces, 5);
            this.setPieces(yellowPieces, 6);
        }
    }
    
    public int[][] getRedTriangle() {
        return redPieces;
    }
    
    public int[][] getBlackTriangle() {
        return blackPieces;
    }
    
    public int[][] getBlueTriangle() {
        return bluePieces;
    }
    
    public int[][] getGreenTriangle() {
        return greenPieces;
    }
    
    public int[][] getYellowTriangle() {
        return yellowPieces;
    }
    
    public int[][] getWhiteTriangle() {
        return whitePieces;
    }
}
