/**
 * Represents a non-bomb tile on the Minesweeper board.
 * Can either display as space or number when revealed.
 */
public class EmptyTile extends Tile {
    // amount of bombs around the tile
    private int otherBombs;

    /**
     * Constructs a new EmptyTile.
     */
    public EmptyTile() {super();}

    /**
     * Returns the symbol of the tile when revealed.
     * @return the string representation of the tile when revealed
     */
    public String show() {
        if (otherBombs == 0) {
            return " ";
        }
        return "" + otherBombs;
    }

    /**
     * Sets the number of bombs around the tile.
     * @param bombs the number of bombs around the tile
     */
    public void setBombs(int bombs) {
        otherBombs = bombs;
    }

    /**
     * Returns the number of bombs around the tile.
     * @return the number of bombs around the tile
     */
    public int getBombs() {
        return otherBombs;
    }

    /**
     * Returns the hash code of the tile.
     * @return the hash code of the tile
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + otherBombs;
        return result;
    }

    /**
     * Compares this tile to another object for equality.
     * @param obj the object to compare with
     * @return true if the tiles are equal, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        EmptyTile other = (EmptyTile) obj;
        if (otherBombs != other.otherBombs)
            return false;
        return true;
    }
}