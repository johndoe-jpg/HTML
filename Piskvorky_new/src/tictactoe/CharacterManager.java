package tictactoe;

import java.awt.Color;
import java.util.ArrayList;

/**
 *
 * @author THUND
 */
public class CharacterManager {

    private static final ArrayList<String> characters = new ArrayList<>();
    private static final ArrayList<Integer> playerIDs = new ArrayList<>();
    private static final ArrayList<Color> playerColors = new ArrayList<>();

    public CharacterManager() {
        characters.add("X");
        characters.add("O");
        characters.add("@");
        characters.add("+");
        characters.add("€");
        characters.add("$");
        characters.add("¤");
        characters.add("÷");
        characters.add("×");
        characters.add("#");
        characters.add("\u00A9");

        playerIDs.add(0, 0);
        playerColors.add(0, Color.red);
        playerIDs.add(1, 1);
        playerColors.add(0, Color.blue);
    }
    
    public static void setCharToPlayer(int playerID, int charID) {
        if(playerID >= 0 && playerID < playerIDs.size()) {
            playerIDs.set(playerID, charID);
        }
    }

    public static Color getColorBySymbol(String symbol) {
        Color ret = Color.black;
        
        int i = characters.indexOf(symbol);
        if(i != -1) {
            int p = playerIDs.indexOf(i);
            if(p != -1) {
                ret = playerColors.get(p);
            }
        }
        
        return ret;
    }

    public static String getSymbolForPlayer(int player) {
        String ret = null;
        if(player >= 0 && player < playerIDs.size()) {
            int id = playerIDs.get(player);
            if(id >= 0 && id < characters.size()) {
                ret = characters.get(id);
            }
        }
        return ret;
    }
    
    public static ArrayList<Integer> getPlayerIDs() {
        return playerIDs;
    }
    
    public static ArrayList<String> getAllSymbols() {
        return characters;
    }
}
