public class MineSweeper {
    private Tile[][] board;
    public MineSweeper(int bombs, int rows, int cols) {
        // initialize board
        board = new Tile[rows][cols];
        for (int i1=0; i1<rows; i1++) {
            for (int i2=0; i2<cols; i2++) {
                double chance = (double) bombs / ((rows-1-i1) * cols + (cols-1-i2));
                if (Math.random()<chance) {
                    board[i1][i2] = new BombTile();
                } else {
                    board[i1][i2] = new EmptyTile();
                }
            }
        }
    }
    //if survived returns true
    //coords in first quadrent
    public boolean reveal(int i1, int i2) {
        if (board[i1][i2] instanceof BombTile) {
            board[i1][i2].reveal();
            return false;
        }
        chain(i1,i2);
        return true;
    }
    //coords in first quadrent
    public void flag(int i1, int i2) {
        board[i1][i2].flag();
    }
    private void chain(int i1, int i2) {
        Tile tile = board[i1][i2];
        //Precondition tile instanceof EmptyTile and i1 and i2 are in bounds
        if (tile instanceof EmptyTile) {
            throw new IllegalArgumentException("chain called with "+i1+"and "+i2);
        }
        tile.reveal();
        int bombs = countBombs(i1, i2);
        EmptyTile et = (EmptyTile) tile;
        et.setBombs(bombs);
        if (tile instanceof EmptyTile) {
            for (int off1=-1; off1<=1; off1++) {
                for (int off2=-1; off2<=1; off2++) {
                    if (!(off1==0 && off2==0)) {
                        chain(i1+off1, i2+off2);
                    }
                }
            } 
        }
    }
    private int countBombs(int i1, int i2) {
        int count = 0;
        for (int off1=-1; off1<=1; off1++) {
            for (int off2=-1; off2<=1; off2++) {
                if (board[i1+off1][i2+off2] instanceof BombTile) {
                    count++;
                }
            }
        }
        return count;
    }
    public void print() {
        for (int row=0; row<board.length; row++) {
            for (int col=0; col<board[row].length; col++) {
                System.out.print(board[row][col]+" ");
            }
            System.out.println();
        }
    }
    // public void removeBomb(int i1, int i2) {
    //     if (!board[i1][i2].isRevealed()) {
    //         throw new IllegalArgumentException("can not remove a bomb there");
    //     }
    // }
    // public void addBomb() {

    // }
}
