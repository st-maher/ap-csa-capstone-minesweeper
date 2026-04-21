public abstract class Tile {
    private boolean revealed=false;
    private boolean flagged=false;
    public Tile() {}
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
    public abstract String show();
    public final void reveal() {revealed=true;}
    public final void flag() {flagged=!flagged;}
    public final boolean isRevealed() {return revealed;}
    public final boolean equals(Object other) {
        if (this==other) return true;
        if (!(this instanceof Tile)) {return false;}
        return (""+this).equals(other+"");
    }
}