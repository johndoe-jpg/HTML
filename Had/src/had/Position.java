package had;

public class Position {
    public int x;
    public int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public void copy(Position p) {
        this.x = p.x;
        this.y = p.y;
    }
    
    public boolean equals(Position p) {
        if (p.x == x && p.y == y) {
            return true;
        }
        return false;
    }
}
