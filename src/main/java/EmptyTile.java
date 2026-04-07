public class EmptyTile  extends Tile{
    private int otherBombs;
    public EmptyTile(){}
    public String show() {
        if (otherBombs == 0) {
            return " ";
        }
        return ""+otherBombs;
    }
    public void setBombs(int bombs) {otherBombs = bombs;}
}