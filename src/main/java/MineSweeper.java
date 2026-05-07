import java.util.Arrays;

public class MineSweeper {
    private Tile[][] board;
    private boolean lost;
    //initializes board
    public MineSweeper(int bombs, int rows, int cols) {
        // initialize board
        board = new Tile[rows][cols];
        for (int i1=0; i1<rows; i1++) {
            for (int i2=0; i2<cols; i2++) {
                double chance = (double) bombs / ((rows-1-i1) * cols + (cols-1-i2));
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
    //reveals tile at board[i1][i2]
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
    //reveals tile at board[i1][i2]
    public void flag(int i1, int i2) {
        board[i1][i2].flag();
    }
    //reveals tiles around board[i1][i2]
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
    //counts bombs around i1 and i2
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
    //prints the board
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
    //prints the fully revealed board
    public void printAll() {
        for (int row=0; row<board.length; row++) {
            for (int col=0; col<board[row].length; col++) {
                System.out.print(board[row][col].show()+" ");
            }
            System.out.println();
        }
    }
    //returns if you lost
    public boolean isLost() {return lost;}
    //returns if your indexes are inbounds
    public boolean inbounds(int i1, int i2) {
        if (0<=i1 && i1<board.length) {
            if (0<=i2 && i2<board.length) {
                return true;
            }
        }
        return false;
    }
    //gets amount of rows in board
    public int len() {
        // TODO Auto-generated method stub
        return board.length;
    }
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
    // calls reveal on a random EmptyTile
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
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + Arrays.deepHashCode(board);
        return result;
    }
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
}
