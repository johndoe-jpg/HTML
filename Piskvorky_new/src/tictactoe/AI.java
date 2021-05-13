package tictactoe;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author THUND
 */
public final class AI {

    private static final Random rand = new Random();
    // Weights for distance from tile (closest to furthest)
//    private static int[] w = new int[]{8, 4, 2, 1};
    private static int[] w = new int[]{5, 6, 7, 8};
//    private static int[] w = new int[]{8, 7, 6, 5};
    private static int fourInARowMultiplier = 1000;
    private static int threeInARowMultiplier = 100;
    private static int myPieceMultiplier = 1;
    private static int enemyPieceMultiplier = 100;

    private AI() {
    }

    public static Point getBestMove(String[][] board, String AIChar) {
        if (board.length == 0 || board[0].length == 0) {
            // Doesn't make sense
            return null;
        }
        // Initialize it to a random move, so if the AI fails it'll do something at least
        Point p = new Point(rand.nextInt(board.length), rand.nextInt(board[0].length));
        
        try {
            int[][] valBoard = calculateValue(board, AIChar);
        
            p = getBestMove(valBoard);
            
        } catch (Exception e) {}

        return p;
    }

    private static int[][] calculateValue(String[][] board, String AIChar) {
        int[][] val = new int[board.length][board[0].length];

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                // Go through every tile
                // Ignore occupied tiles, we can't move on them
                if (board[i][j] == null || board[i][j].equals("")) {
                    // For every non-occupied tile, look at the next 4 tiles in every direction
                    int tl, t, tr, l, r, bl, b, br;
                    // Differentiate between "my" and "enemy" pieces
                    int etl, et, etr, el, er, ebl, eb, ebr;
                    // Count the pieces and multiply by weights based on distance to current tile (right next to tile = 8, 4 tiles away = 1
                    // If there are 3 or 4 pieces in a row, prioritize them

                    Point tmp;
                    tmp = getWeightedPiecesFromDir(board, i, j, -1, -1, AIChar);
                    tl = tmp.x;
                    etl = tmp.y;

                    tmp = getWeightedPiecesFromDir(board, i, j, +0, -1, AIChar);
                    t = tmp.x;
                    et = tmp.y;

                    tmp = getWeightedPiecesFromDir(board, i, j, +1, -1, AIChar);
                    tr = tmp.x;
                    etr = tmp.y;

                    tmp = getWeightedPiecesFromDir(board, i, j, -1, +0, AIChar);
                    l = tmp.x;
                    el = tmp.y;

                    tmp = getWeightedPiecesFromDir(board, i, j, +1, +0, AIChar);
                    r = tmp.x;
                    er = tmp.y;

                    tmp = getWeightedPiecesFromDir(board, i, j, -1, +1, AIChar);
                    bl = tmp.x;
                    ebl = tmp.y;

                    tmp = getWeightedPiecesFromDir(board, i, j, +0, +1, AIChar);
                    b = tmp.x;
                    eb = tmp.y;

                    tmp = getWeightedPiecesFromDir(board, i, j, +1, +1, AIChar);
                    br = tmp.x;
                    ebr = tmp.y;
                    
                    

                    int c =  ((tl + t + tr + l + r + bl + b + br) / 16) + ((etl + et + etr + el + er + ebl + eb + ebr) * 8);
                    
                    val[i][j] = c;
                }
            }
        }
        // printArr(val);
        return val;
    }

    private static Point getWeightedPiecesFromDir(String[][] board, int x, int y, int dirx, int diry, String myChar) {
        int myChars = 0;
        int enemyChars = 0;
        int myCharsUnBias = 0;
        int enemyCharsUnBias = 0;

        for (int i = 1; i <= 4; i++) {
            int nx = x + (dirx * i);
            int ny = y + (diry * i);
            if (nx >= 0 && ny >= 0 && nx < board.length && ny < board[0].length) {
                if (board[nx][ny] == null) {
                    // empty tile
                    // possibilities: End the loop, continue the loop
                    break;
                } else if (board[nx][ny].equals(myChar)) {
                    // my char
                    myChars += w[i - 1];
                    myCharsUnBias++;
                } else {
                    // enemy char
                    enemyChars += w[i - 1];
                    enemyCharsUnBias++;
                }
            }
        }
        Point end = new Point(myChars, enemyChars);
        if(myCharsUnBias == 4 || enemyCharsUnBias == 4) {
            end.x = end.x * fourInARowMultiplier * myPieceMultiplier;
            end.y = end.y * fourInARowMultiplier * enemyPieceMultiplier;
        }
        if(myCharsUnBias == 3 || enemyCharsUnBias == 3) {
            
            end.x = end.x * threeInARowMultiplier * myPieceMultiplier;
            end.y = end.y * threeInARowMultiplier * enemyPieceMultiplier;
        }
        
        return end;
    }
    
    private static void printArr(int[][] ar) {
        for (int i = 0; i < ar.length; i++) {
            for (int j = 0; j < ar[i].length; j++) {
                if(ar[j][i] < 10 && ar[j][i] >= 0) {
                    System.out.print("  ");
                }
                if(ar[j][i] >= 10 || (ar[j][i] <= -1 && ar[j][i] > -10)) {
                    System.out.print(" ");
                } 
                System.out.print(ar[j][i] + " ");
            }
            System.out.println("");
        }
        System.out.println("");
    }
    
    private static Point getBestMove(int[][] ar) {
        ArrayList<Point> bestMoves = new ArrayList<>();
        int high = -1;
        for (int i = 0; i < ar.length; i++) {
            for (int j = 0; j < ar[i].length; j++) {
                 if(ar[j][i] == high) {
                     bestMoves.add(new Point(j, i));
                 }
                 if(ar[j][i] > high) {
                     bestMoves.clear();
                     bestMoves.add(new Point(j, i));
                     high = ar[j][i];
                 }
            }
        }
        return bestMoves.get(rand.nextInt(bestMoves.size()));
    }
}
