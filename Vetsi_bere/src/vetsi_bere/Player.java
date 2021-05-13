package vetsi_bere;

import java.util.ArrayList;

/**
 *
 * @author THUND
 */
public class Player {
    
    private String name;
    private final ArrayList<Card> deck = new ArrayList<>();
    private Card lastPulledCard;
    private boolean canDraw = true;
    private boolean outOfCards = false;

    public Player(String name) {
        this.name = name;
    }
    
    public void addCard(Card card) {
        outOfCards = false;
        deck.add(card);
    }
    
    public Card drawCard() {
        if (deck.size() > 0) {
            if(deck.size() == 1) {
                outOfCards = true;
            }
            lastPulledCard = deck.get(0);
            return deck.remove(0);
        }
        
        return null;
    }
    
    public Card peek() {
        return lastPulledCard;
    }
    
    public boolean isOutOfCards() {
        return outOfCards;
    }
    
    public boolean canDraw() {
        return canDraw;
    }
    
    public void setCanDraw(boolean b) {
        canDraw = b;
    }
    
    public int cardsLeft() {
        return deck.size();
    }

    public void dumpDeck() {
        deck.clear();
    }
    
    @Override
    public String toString() {
        return name;
    }
}
