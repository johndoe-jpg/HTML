package checkers;

/**
 *
 * @author THUND
 */
public class Piece {
    
    private boolean isWhite;
    private boolean selected;
    private boolean queen;

    /**
     * 
     * @param isWhite 
     */
    public Piece(boolean isWhite) {
        this.isWhite = isWhite;
        this.selected = false;
        this.queen = false;
    }

    public boolean isWhite() {
        return isWhite;
    }

    public boolean isSelected() {
        return selected;
    }

    public boolean isQueen() {
        return queen;
    }
    
    public void upgradeToQueen() {
        queen = true;
    }
    
    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
