import java.util.Arrays;

/**
 * Represents the Minesweeper game board.
 * Board consists of a grid of tiles, which can be either bomb tiles or empty tiles.
 */
public class MineSweeper {
    private Tile[][] board;
    private boolean lost;

    /**
     * Constructs a new Minesweeper board with the specified number of bombs and dimensions.
     * The board is initialized with the specified number of bombs randomly placed.
     * Every position has an equal chance of having a bomb, 
     * and the number of bombs is guaranteed to be correct.
     * 
     * @param bombs the number of bombs to place on the board
     * @param rows the number of rows in the board
     * @param cols the number of columns in the board
     */
    //initializes board
    public MineSweeper(int bombs, int len) {
        // initialize board
        board = new Tile[len][len];
        for (int i1=0; i1<len; i1++) {
            for (int i2=0; i2<len; i2++) {
                double chance = (double) bombs / ((len-1-i1) * len + (len-1-i2+1));
                if (Math.random()<chance) {
                    board[i1][i2] = new BombTile();
                    bombs-=1;
                } else {
                    board[i1][i2] = new EmptyTile();
                }
            }
        }
        lost = false;
    }

    /**
     * Reveals the tile at the specified position on the board.
     * @param i1 row index of the tile to reveal
     * @param i2 column index of the tile to reveal
     */
    public void reveal(int i1, int i2) {
        if (board[i1][i2].isFlagged()) {
            System.out.println("You can not reveal flagged tiles (enter F {position} to unflag the tile).");
            return;
        }
        if (board[i1][i2] instanceof BombTile) {
            board[i1][i2].reveal();
            lost = true;
        }
        chain(i1,i2);
    }
    
    /**
     * Toggles the flagged state of the tile at the specified position on the board.
     * 
     * @param i1 row index of the tile to flag
     * @param i2 column index of the tile to flag
     */
    public void flag(int i1, int i2) {
        board[i1][i2].flag();
    }
    /**
     * Flood fill reveals tiles around the specified position on the board.
     * Tile must be an instance of EmptyTile and the position must be in bounds.
     * 
     * @param i1 row index of the tile to start the flood fill
     * @param i2 column index of the tile to start the flood fill
     */
    private void chain(int i1, int i2) {
        Tile tile = board[i1][i2];

        if (tile.isRevealed()) {return;}

        //Precondition tile instanceof EmptyTile and i1 and i2 are in bounds
        if (tile instanceof BombTile) {
            throw new IllegalArgumentException("chain called with "+i1+" and "+i2);
        }
        tile.reveal();
        int bombs = countBombs(i1, i2);
        EmptyTile et = (EmptyTile) tile;
        et.setBombs(bombs);
        if (et.getBombs()==0) {
            for (int off1=-1; off1<=1; off1++) {
                for (int off2=-1; off2<=1; off2++) {
                    if (!(off1==0 && off2==0)) {
                        if (inbounds(i1+off1, i2+off2)) {
                            chain(i1+off1, i2+off2);
                        }
                    }
                }
            } 
        }
    }

    /**
     * Counts the number of bombs around the specified position on the board.
     * Tile must be an instance of EmptyTile and the position must be in bounds.
     * 
     * @param i1 row index of the tile to count bombs around
     * @param i2 column index of the tile to count bombs around
     * @return the number of bombs around the specified position
     */
    private int countBombs(int i1, int i2) {
        if (!(board[i1][i2] instanceof EmptyTile)) {throw new IllegalArgumentException("countBombs method is not supposed to be called on a bomb tile");}
        int count = 0;
        for (int off1=-1; off1<=1; off1++) {
            for (int off2=-1; off2<=1; off2++) {
                if (inbounds(i1+off1, i2+off2) && board[i1+off1][i2+off2] instanceof BombTile) {
                    count++;
                }
            }
        }
        return count;
    }
    /**
     * Prints the current state of the board.
     */
    public void print() {
        System.out.print("  ");
        for (int col=0; col<board[0].length; col++) {
            System.out.print(col+" ");
        }
        System.out.println();
        for (int row=0; row<board.length; row++) {
            System.out.print(row+" ");
            for (int col=0; col<board[row].length; col++) {
                System.out.print(board[row][col]+" ");
            }
            System.out.println();
        }
    }
    /**
     * Prints the fully revealed board.
     */
    public void printAll() {
        for (int row=0; row<board.length; row++) {
            for (int col=0; col<board[row].length; col++) {
                System.out.print(board[row][col].show()+" ");
            }
            System.out.println();
        }
    }
    /**
     * Returns whether the player has lost the game.
     * @return true if the player has lost, false otherwise
     */
    public boolean isLost() {return lost;}

    /**
     * Checks if the specified indexes are within the bounds of the board.
     * @param i1 row index to check
     * @param i2 column index to check
     * @return true if the indexes are within bounds, false otherwise
     */
    public boolean inbounds(int i1, int i2) {
        if (0<=i1 && i1<board.length) {
            if (0<=i2 && i2<board.length) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if the player has won the game.
     * Can't be called if the player has lost.
     * 
     * @return true if the player has won, false otherwise
     */
    //checks if all EmptyTiles are revealed
    public boolean isWon() {
        if (lost) {throw new IllegalStateException("You can't call isWon if you lost");}
        for (Tile[] row: board) {
            for (Tile tile: row) {
                if (tile instanceof EmptyTile && !tile.isRevealed()) {
                    return false;
                }
            }
        }
        return !lost;
    }
    /**
     * Reveals non-bomb tiles randomly until the player has won.
     */
    public void help() {
        while (!isWon()) {
            int i1 = (int) (Math.random()*board.length);
            int i2 = (int) (Math.random()*board[i1].length);
            if (board[i1][i2] instanceof EmptyTile && !board[i1][i2].isRevealed()) {
                reveal(i1, i2);
                return;
            }
        }
    }
    /**
     * Returns the hash code of the board.
     * @return the hash code of the board
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + Arrays.deepHashCode(board);
        return result;
    }
    /**
     * Compares this board to another object for equality.
     * @param obj the object to compare with
     * @return true if the boards are equal, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        MineSweeper other = (MineSweeper) obj;
        if (!Arrays.deepEquals(board, other.board))
            return false;
        return true;
    }
    public int getLen() {return board.length;}
}
