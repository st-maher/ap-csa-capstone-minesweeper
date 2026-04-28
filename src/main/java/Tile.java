public abstract class Tile {
    private boolean revealed=false;
    private boolean flagged=false;
    public Tile() {}
    // print tile
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
    //get revealed symbol
    public abstract String show();
    //reveal tile
    public final void reveal() {revealed=true;}
    //toggle isFlagged
    public final void flag() {flagged=!flagged;}
    public final boolean isRevealed() {return revealed;}
    public final boolean isFlagged() {return flagged;}
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (revealed ? 1231 : 1237);
        result = prime * result + (flagged ? 1231 : 1237);
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
        Tile other = (Tile) obj;
        if (revealed != other.revealed)
            return false;
        if (flagged != other.flagged)
            return false;
        return true;
    }
    
}