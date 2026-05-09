/**
 * Represents a bomb tile on the Minesweeper board.
 * Always displays as "B" when revealed.
 */
public class BombTile extends Tile {
    /**
     * Constructs a new BombTile.
     */
    public BombTile() {}

    /**
     * Returns the symbol of the tile when revealed.
     * @return the string representation of the tile when revealed
     */
    public String show() {return "B";}
}