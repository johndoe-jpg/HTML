package tictactoe;

import java.awt.Point;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 *
 * @author THUND
 */
public class GameManager implements PropertyChangeListener {

    private String[][] board;
    private boolean firstPlayerTurn = true;
    private boolean gameOver = false;
    private int moves = 0;
    private boolean aiEnemy = false;

    public GameManager(int x, int y) {
        generateNewBoard(x, y);
    }
    
    private void generateNewBoard() {
        generateNewBoard(board.length, board[0].length);
    }
    
    private void generateNewBoard(int x, int y) {
        board = new String[x][y];
    }

    private void clickHandler(Point clk) {
        if (board[clk.x][clk.y] != null || gameOver) {
            return;
        }

        int player = (firstPlayerTurn) ? 0 : 1;
        board[clk.x][clk.y] = CharacterManager.getSymbolForPlayer(player);
        moves++;
        firstPlayerTurn = !firstPlayerTurn;
        notifyChange();
        
        checkWin(clk);
        
        if(aiEnemy && !firstPlayerTurn) {
            Point aiMove = AI.getBestMove(board, CharacterManager.getSymbolForPlayer(1));
            
            clickHandler(aiMove);
        }
    }
    
    private void checkWin(Point origin) {
        // Coordinates to be examined
        int x = origin.x;
        int y = origin.y;
        if(
                // TopLeft - BottomRight
                (getPieceCountByDir(x, y,  1, 1) >= 5) ||
                // TopRight - BottomLeft
                (getPieceCountByDir(x, y, -1, 1) >= 5) ||
                // Left - Right
                (getPieceCountByDir(x, y,  0, 1) >= 5) ||
                // Top - Bottom
                (getPieceCountByDir(x, y,  1, 0) >= 5)) {
            // Victory
            String win = "";
            if(!firstPlayerTurn) {
                win = "Hráč 1";
            } else {
                win = (aiEnemy) ? "Počítač" : "Hráč 2";
            }
            gameOver = true;
            propertyChangeSupport.firePropertyChange("gameOver", null, win);
        }
        
        if (moves == board.length * board[0].length) {
            // ran out of moves
            gameOver = true;
            propertyChangeSupport.firePropertyChange("gameOver", null, "Nikdo");
        }
    }
    
    private int getPieceCountByDir(int ogx, int ogy, int xDir, int yDir) {
        int c = 1;
        String comp = board[ogx][ogy]; // String to compare
        
        // Look in a direction until end of board/same pieces
        int x = ogx + xDir;
        int y = ogy + yDir;
        while(x >= 0 && x < board.length && y >= 0 && y < board[0].length && board[x][y]!= null && board[x][y].equals(comp)) {
            c++;
            x += xDir;
            y += yDir;
        }
        
        // Look in the opposite direction
        x = ogx - xDir;
        y = ogy - yDir;
        while(x >= 0 && x < board.length && y >= 0 && y < board[0].length && board[x][y]!= null && board[x][y].equals(comp)) {
            c++;
            x -= xDir;
            y -= yDir;
        }
        
        return c;
    }
    
    private void notifyChange() {
        propertyChangeSupport.firePropertyChange("repaint", null, board);
        propertyChangeSupport.firePropertyChange("activeSymbol", CharacterManager.getSymbolForPlayer((!firstPlayerTurn) ? 0 : 1), CharacterManager.getSymbolForPlayer((firstPlayerTurn) ? 0 : 1));
    }

    public void newGame(Point boardSize, boolean ai) {
        generateNewBoard(boardSize.x, boardSize.y);
        aiEnemy = ai;
        firstPlayerTurn = true;
        gameOver = false;
        moves = 0;
        propertyChangeSupport.firePropertyChange("repaint", null, board);
        notifyChange();
   }

    private transient final PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.removePropertyChangeListener(listener);
    }

    @Override
    public void propertyChange(PropertyChangeEvent pce) {
        if (pce.getPropertyName().equals("clickEvt")) {
            Point clk = (Point) pce.getNewValue();
            if (clk == null) {
                return;
            }

            clickHandler(clk);
        }
        
        if (pce.getPropertyName().equals("reset")) {
            generateNewBoard();
            notifyChange();
            gameOver = false;
            moves = 0;
            
            if(aiEnemy && !firstPlayerTurn) {
                Point aiMove = AI.getBestMove(board, CharacterManager.getSymbolForPlayer(1));
            
                clickHandler(aiMove);
            }
        }
    }
}
