package pkg4ita_machado_2048;

import java.awt.Point;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author THUND
 */
public class GameManager {

    // private final ArrayList<Integer> board = new ArrayList<>();
    private int[][] board;
    private final Random r = new Random();
    private final ArrayList<Point> movedTiles = new ArrayList<>();
    private boolean gameOver = false;

    private transient final PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.removePropertyChangeListener(listener);
    }


    public void newGame(int x, int y) {
        board = new int[x][y];
        gameOver = false;
        generateCell(2);
        generateCell(2);
    }
    
    private void gameOver(boolean win) {
        gameOver = true;
        if(win) {
            System.out.println("win");
        } else {
            System.out.println("lost");
        }
        propertyChangeSupport.firePropertyChange("over", null, win);
    }

    private void generateCell() {
        generateCell(0);
    }

    private void generateCell(int customVal) {
        int value;
        if (customVal == 0) {
            value = (r.nextInt(100) <= 70) ? 2 : 4;
        } else {
            value = customVal;
        }

        ArrayList<Point> empty = getEmptyTiles();
        if (empty.isEmpty()) {
            return;
        }
        Point emptyTile = empty.get(r.nextInt(empty.size()));
        board[emptyTile.x][emptyTile.y] = value;
    }

    private ArrayList<Point> getEmptyTiles() {
        ArrayList<Point> tiles = new ArrayList<>();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == 0) {
                    tiles.add(new Point(i, j));
                }
            }
        }
        return tiles;
    }

    public void move(Direction dir) {
        if(gameOver) {
            return;
        }
        move(dir, board);
        if(is2048()) {
            gameOver(true);
        }
        generateCell();
        if(!avalibleMoves()) {
            gameOver(false);
        }
//        System.out.println(toString());
    }

    public void move(Direction dir, int[][] board) {
        movedTiles.clear();

        switch (dir) {
            case NORTH:
                for (int i = 0; i < board.length; i++) {
                    for (int j = 0; j < board[i].length; j++) {
                        if (!isTileInBounds(i, j, board) || board[i][j] == 0) {
                            continue;
                        }

                        northMove(i, j, board);
                    }
                }
                break;
            case EAST:
                for (int i = board.length - 1; i >= 0; i--) {
                    for (int j = 0; j < board[i].length; j++) {
                        if (!isTileInBounds(i, j, board) || board[i][j] == 0) {
                            continue;
                        }

                        eastMove(i, j, board);
                    }
                }
                break;
            case SOUTH:
                for (int i = 0; i < board.length; i++) {
                    for (int j = board[i].length - 1; j >= 0; j--) {
                        if (!isTileInBounds(i, j, board) || board[i][j] == 0) {
                            continue;
                        }

                        southMove(i, j, board);
                    }
                }
                break;
            case WEST:
                for (int i = 0; i < board.length; i++) {
                    for (int j = 0; j < board[i].length; j++) {
                        if (!isTileInBounds(i, j, board) || board[i][j] == 0) {
                            continue;
                        }

                        westMove(i, j, board);
                    }
                }
                break;
            default:
                throw new AssertionError();
        }
    }

    private void northMove(int a, int b, int[][] board) {
        Point lastPos = new Point(a, b);

        for (int k = b - 1; k < board[0].length; k--) {
            lastPos = vertMoveCheck(a, b, k, lastPos, board);
            if (lastPos == null) {
                return;
            }
        }
    }

    private void westMove(int a, int b, int[][] board) {
        Point lastPos = new Point(a, b);

        for (int k = a - 1; k < board.length; k--) {
            lastPos = horizMoveCheck(a, b, k, lastPos, board);
            if (lastPos == null) {
                return;
            }
        }
    }

    private void eastMove(int a, int b, int[][] board) {
        Point lastPos = new Point(a, b);

        for (int k = a + 1; k >= 0; k++) {
            lastPos = horizMoveCheck(a, b, k, lastPos, board);
            if (lastPos == null) {
                return;
            }
        }
    }

    private void southMove(int a, int b, int[][] board) {
        Point lastPos = new Point(a, b);

        for (int k = b + 1; k >= 0; k++) {
            lastPos = vertMoveCheck(a, b, k, lastPos, board);
            if (lastPos == null) {
                return;
            }
        }
    }

    private Point vertMoveCheck(int a, int b, int k, Point lastPos, int[][] board) {
        if (!isTileInBounds(a, k, board) || (board[a][k] != board[a][b] && board[a][k] != 0)) {
            moveAndReplaceTile(a, b, lastPos.x, lastPos.y, 1, board);
            return null;
        }

        if (board[a][b] == board[a][k]) {
            if (hasTileCombined(a, k)) {
                moveAndReplaceTile(a, b, lastPos.x, lastPos.y, 1, board);
            } else {
                moveAndReplaceTile(a, b, a, k, 2, board);
                movedTiles.add(new Point(a, k));
            }
            return null;
        }

        lastPos.setLocation(a, k);
        return lastPos;
    }

    private Point horizMoveCheck(int a, int b, int k, Point lastPos, int[][] board) {
        if (!isTileInBounds(k, b, board) || (board[k][b] != board[a][b] && board[k][b] != 0)) {
            moveAndReplaceTile(a, b, lastPos.x, lastPos.y, 1, board);
            return null;
        }

        if (board[a][b] == board[k][b]) {
            if (hasTileCombined(k, b)) {
                moveAndReplaceTile(a, b, lastPos.x, lastPos.y, 1, board);
            } else {
                moveAndReplaceTile(a, b, k, b, 2, board);
                movedTiles.add(new Point(k, b));
            }
            return null;
        }

        lastPos.setLocation(k, b);
        return lastPos;
    }

    private boolean hasTileCombined(int i, int j) {
        for (int k = 0; k < movedTiles.size(); k++) {
            Point p = movedTiles.get(k);
            if (p.x == i && p.y == j) {
                return true;
            }
        }
        return false;
    }

    private boolean isTileInBounds(int x, int y, int[][] board) {
        if (board.length == 0 || board[0].length == 0) {
            return false;
        }
//        System.out.println(x + ", " + y + " = " + !(x < 0 || x >= board.length || y < 0 || y >= board[0].length));
        return !(x < 0 || x >= board.length || y < 0 || y >= board[0].length);
    }

    private void moveAndReplaceTile(int ogx, int ogy, int nex, int ney, int multiplier, int[][] board) {
        if (ogx == nex && ogy == ney) {
            return;
        }
        if (isTileInBounds(ogx, ogy, board) && isTileInBounds(nex, ney, board)) {
//            System.out.println("[" + nex + ", " + ney + "] <- [" + ogx + ", " + ogy + "] * " + multiplier);
            board[nex][ney] = board[ogx][ogy] * multiplier;
            board[ogx][ogy] = 0;
        }
    }

    private boolean avalibleMoves() {
        int[][] copy = new int[board.length][board[0].length];
        cloneBoard(board, copy);
        boolean didntMove = true;

        move(Direction.NORTH, copy);
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] != copy[i][j]) {
                    didntMove = false;
                }
            }
        }
        if (didntMove) {
            cloneBoard(board, copy);
            move(Direction.EAST, copy);
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[i].length; j++) {
                    if (board[i][j] != copy[i][j]) {
                        didntMove = false;
                    }
                }
            }
        }
        if (didntMove) {
            cloneBoard(board, copy);
            move(Direction.SOUTH, copy);
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[i].length; j++) {
                    if (board[i][j] != copy[i][j]) {
                        didntMove = false;
                    }
                }
            }
        }
        if (didntMove) {
            cloneBoard(board, copy);
            move(Direction.WEST, copy);
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[i].length; j++) {
                    if (board[i][j] != copy[i][j]) {
                        didntMove = false;
                    }
                }
            }
        }

        return !didntMove;
    }

    private void cloneBoard(int[][] og, int[][] copyTo) {
        for (int i = 0; i < og.length; i++) {
            for (int j = 0; j < og[i].length; j++) {
                copyTo[i][j] = og[i][j];
            }
        }
    }
    
    private boolean is2048() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if(board[i][j] >= 2048) {
                    return true;
                }
            }
        }
        return false;
    }
    
    public int[][] getBoard() {
        return board;
    }

    @Override
    public String toString() {
        String s = "";
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                s += board[j][i] + ", ";
            }
            s += "\n";
        }
        return s;
    }
}

enum Direction {
    NORTH,
    EAST,
    SOUTH,
    WEST,
}
