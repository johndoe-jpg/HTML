package snake_machado;

import java.awt.Point;

/**
 *
 * @author THUND
 */
public final class Direction {
    
    public static final Point NORTH = new Point(0, -1);
    public static final Point EAST = new Point(-1, 0);
    public static final Point SOUTH = new Point(0, 1);
    public static final Point WEST = new Point(1, 0);
    
    private Direction() {}
}
