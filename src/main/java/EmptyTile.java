public class EmptyTile  extends Tile{
    //amount of bombs around the tile
    private int otherBombs;
    public EmptyTile(){}
    public String show() {
        if (otherBombs == 0) {
            return " ";
        }
        return ""+otherBombs;
    }
    public void setBombs(int bombs) {otherBombs = bombs;}
    public int getBombs() {return otherBombs;}
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + otherBombs;
        return result;
    }
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