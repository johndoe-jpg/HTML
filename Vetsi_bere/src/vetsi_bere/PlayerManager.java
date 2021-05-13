package vetsi_bere;

import java.util.ArrayList;

/**
 *
 * @author THUND
 */
public final class PlayerManager {
    
    public static final ArrayList<Player> players = new ArrayList<>();
    
    public static void clear() {
        players.clear();
    }
    
    public static void addPlayer(String name) {
        players.add(new Player(name));
    }
    
    public static ArrayList<Player> getPlayers() {
        return players;
    }
    
    public static boolean onlyOnePlayerHasCards() {
        int pWithCards = 0;
        
        for (int i = 0; i < players.size(); i++) {
            if(players.get(i).cardsLeft() != 0) {
                pWithCards++;
            }
        }
        
        return pWithCards == 1;
    }
    
    public static Player getFirstPlayerWithCards () {
        for (int i = 0; i < players.size(); i++) {
            if(players.get(i).cardsLeft() != 0) {
                return players.get(i);
            }
        }
        return null;
    }
    
    public static void dumpDecks() {
        for (int i = 0; i < players.size(); i++) {
            players.get(i).dumpDeck();
        }
    }
}
