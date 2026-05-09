/**
 * Individual tile on the Minesweeper board. 
 * Each tile can be revealed or flagged, 
 * and has a specific symbol that is displayed when revealed.
 */
public abstract class Tile {
    private boolean revealed = false;
    private boolean flagged = false;

    /**
     * Constructs a new Tile.
     */
    public Tile() {
    }

    /**
     * Returns a string representation of the tile.
     * If the tile is revealed, it shows the tile's symbol.
     * If the tile is flagged, it shows "F".
     * Otherwise, it shows "X".
     */
    public final String toString() {
        if (revealed) {
            return show();
        }
        if (flagged) {
            return "F";
        } else {
            return "X";
        }
    }

    /**
     * Returns the symbol of the tile when revealed.
     */
    public abstract String show();

    /**
     * Reveals the tile.
     */
    public final void reveal() {
        revealed = true;
    }

    /**
     * Toggles the flagged state of the tile.
     */
    public final void flag() {
        flagged = !flagged;
    }
    /**
     * Returns whether the tile is revealed.
     * @return true if the tile is revealed, false otherwise
     */
    public final boolean isRevealed() {
        return revealed;
    }

    /**
     * Returns whether the tile is flagged.
     * @return true if the tile is flagged, false otherwise
     */
    public final boolean isFlagged() {
        return flagged;
    }

    /**
     * Returns the hash code of the tile.
     * @return the hash code of the tile
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (revealed ? 1231 : 1237);
        result = prime * result + (flagged ? 1231 : 1237);
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
        if (getClass() != obj.getClass())
            return false;
        Tile other = (Tile) obj;
        if (revealed != other.revealed)
            return false;
        if (flagged != other.flagged)
            return false;
        return true;
    }

}