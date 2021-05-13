package snake_machado;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.Timer;

/**
 *
 * @author THUND
 */
public class GameManager implements PropertyChangeListener {
    private Point boardSize = new Point();
    private ArrayList<Point> body = new ArrayList<>();
    private int startBodySize = 2;
    private int bodySize = startBodySize;
    private Point head = new Point();
    private Point apple = new Point();
    private Point dir = new Point();
    private boolean changedDir = false;
    private Timer t;
    private final Random ran = new Random();
    
    public GameManager() {
        int x = SettingsManager.getxSize();
        int y = SettingsManager.getySize();
        boardSize.setLocation(x, y);
        
        drawBoard();
        
        t = new Timer(SettingsManager.getSpeed(), new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                tick();
            }
        });
    }
    
    public void newGame() {
        int x = SettingsManager.getxSize();
        int y = SettingsManager.getySize();
        boardSize.setLocation(x, y);
        head.move(x / 2, y / 2);
        body.clear();
        bodySize = startBodySize;
        changeDir(Direction.NORTH);
        changedDir = false;
        changeDir(Direction.WEST);
        spawnApple();
        t.setDelay(SettingsManager.getSpeed());
        t.start();
        drawBoard();
    }
    
    private void tick() {
        body.add(new Point(head));
        head.move(head.x + dir.x, head.y + dir.y);
        
        if(isHeadInApple()) {
            bodySize++;
            if(bodySize + startBodySize - 1 >= boardSize.x * boardSize.y) {
                gameOver(true);
                System.out.println("WIN: " + (bodySize - startBodySize));
                drawBoard();
                return;
            }
            spawnApple();
        }
        
        if(body.size() > bodySize) {
            body.remove(0);
        }
        
        if(isInsideBody(head) || isHeadOOB()) {
            gameOver(false);
            System.out.println("END: " + (bodySize - startBodySize));
            drawBoard();
            return;
        }
        
        changedDir = false;
        drawBoard();
    }
    
    private boolean isInsideBody(Point tile) {
        for (int i = 0; i < body.size(); i++) {
            if(tile.equals(body.get(i))) {
                return true;
            }
        }
        return false;
    }
    
    private boolean isHeadOOB() { // is head out of bounds
        return (head.x < 0 || head.y < 0 || head.x >= boardSize.x || head.y >= boardSize.y);
    }
    
    private boolean isHeadInApple() {
        return (head.x == apple.x && head.y == apple.y);
    }
    
    
    private void gameOver(boolean win) {
        t.stop();
        pcs.firePropertyChange("gameOver", -1, bodySize - startBodySize);
    }
    
    private void spawnApple() {
        boolean fast = bodySize < (2 * (boardSize.x * boardSize.y)) / 3;
        
        // Fast method is only fast when there is a large amount of empty tiles
        Point tmp = new Point();
        if(fast) {
            do {                
                tmp.x = ran.nextInt(boardSize.x);
                tmp.y = ran.nextInt(boardSize.y);
            } while (isInsideBody(tmp));
        } else {
            ArrayList<Point> empty = new ArrayList<>();
            for (int i = 0; i < boardSize.x; i++) {
                for (int j = 0; j < boardSize.y; j++) {
                    tmp.move(i, j);
                    if(!head.equals(tmp) && !isInsideBody(tmp)) {
                        empty.add(new Point(tmp));
                    }
                }
            }
            tmp.setLocation(empty.get(ran.nextInt(empty.size())));
        }
        
        apple.setLocation(tmp);
    }
    
    private void changeDir(Point dir) {
        if(!changedDir) {
            if(this.dir.x != -dir.x) {
                this.dir.move(dir.x, 0);
            }
            if(this.dir.y != -dir.y) {
                this.dir.move(0, dir.y);
            }
            changedDir = true;
        }
    }
    
    /**
     * 0 - air
     * 1 - snake
     * 2 - apple
     * 3 - head north
     * 4 - head east
     * 5 - head south
     * 6 - head west
     */
    private void drawBoard() {
        int[][] board = new int[boardSize.x][boardSize.y];
        
        for (int i = 0; i < body.size(); i++) {
            int x = body.get(i).x;
            int y = body.get(i).y;
            board[x][y] = 1;
        }
        
        board[apple.x][apple.y] = 2;
        
        if(!isHeadOOB()) {
            if(dir.equals(Direction.NORTH)) {
                board[head.x][head.y] = 3;
            } else if (dir.equals(Direction.EAST)) {
                board[head.x][head.y] = 4;
            } else if (dir.equals(Direction.SOUTH)) {
                board[head.x][head.y] = 5;
            } else if (dir.equals(Direction.WEST)) {
                board[head.x][head.y] = 6;
            }
        }
                
        pcs.firePropertyChange("paintBoard", null, board);
    }

    private transient final PropertyChangeSupport pcs = new PropertyChangeSupport(this);
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        pcs.addPropertyChangeListener(listener);
    }
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        pcs.removePropertyChangeListener(listener);
    }

    @Override
    public void propertyChange(PropertyChangeEvent pce) {
        if(pce.getPropertyName().equals("changeDir")) {
            changeDir((Point) pce.getNewValue());
        }
        if(pce.getPropertyName().equals("backToMenu")) {
            t.stop();
        }
    }
}
