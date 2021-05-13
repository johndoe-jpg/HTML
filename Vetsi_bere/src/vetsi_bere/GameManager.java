package vetsi_bere;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author THUND
 */
public class GameManager {
    
    private static final ArrayList<Card> deck = new ArrayList<>();
    private final Random r = new Random();

    private transient final PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.removePropertyChangeListener(listener);
    }

    
    public void setUpGame() {
        generateDeck();
        scrambleDeck(10000);
        PlayerManager.dumpDecks();
        dealDeck();
    }
    
    public void pullCards() {
        propertyChangeSupport.firePropertyChange("drawPull", null, true);
        
//        System.out.println("Start pull, cards at the beginning:");
        ArrayList<drawnCard> highestCards = new ArrayList<>();
        ArrayList<drawnCard> gameDeck = new ArrayList<>();
        int tmpSize;
        
        // for each player that canDraw, pull a card and save it as a 'drawnCard' into gameDeck
        for (int i = 0; i < PlayerManager.getPlayers().size(); i++) {
//            System.out.println(PlayerManager.getPlayers().get(i) + " (" + PlayerManager.getPlayers().get(i).cardsLeft() + ")");
            if(PlayerManager.getPlayers().get(i).canDraw() && !PlayerManager.getPlayers().get(i).isOutOfCards()) {
                gameDeck.add(new drawnCard(PlayerManager.getPlayers().get(i), PlayerManager.getPlayers().get(i).drawCard()));
            }
        }
        
//        System.out.println("Pulled cards:");
        // Assume the first card is the biggest, skips checking if gameDeck is empty
        highestCards.add(gameDeck.get(0));
//        System.out.println(gameDeck.get(0).card.getSysValue() + " (" + gameDeck.get(0).owner + ")");
        for (int i = 1; i < gameDeck.size(); i++) {
//            System.out.println(gameDeck.get(i).card.getSysValue() + " (" + gameDeck.get(i).owner + ")");
            // This card is bigger than all the cards in the highestCards deck (all these cards have the same value)
            if(gameDeck.get(i).card.getSysValue() > highestCards.get(0).card.getSysValue()) {
                // throw out all the values from the highestCards deck
                highestCards.clear();
                highestCards.add(gameDeck.get(i));
            } else if(gameDeck.get(i).card.getSysValue() == highestCards.get(0).card.getSysValue()) {
                highestCards.add(gameDeck.get(i));
            }
        }
        
        String result = "";
        
        // compare values
        // if no doubles
        if(highestCards.size() == 1) {
//            System.out.println("Round winner:" + highestCards.get(0).owner);
            result += highestCards.get(0).owner + " vyhráli kolo!";
            // largest gets all cards in gameDeck and deck (for loop + remove)
            tmpSize = gameDeck.size();
            for (int i = 0; i < tmpSize; i++) {
                highestCards.get(0).owner.addCard(gameDeck.remove(0).card);
            }
            tmpSize = deck.size();
            for (int i = 0; i < tmpSize; i++) {
                highestCards.get(0).owner.addCard(deck.remove(0));
            }
            // Reset all canDraw
            for (int i = 0; i < PlayerManager.getPlayers().size(); i++) {
                PlayerManager.getPlayers().get(i).setCanDraw(true);
//                System.out.println(PlayerManager.getPlayers().get(i) + " (" + PlayerManager.getPlayers().get(i).cardsLeft() + ")");
            }
        } else {
            // we will wait until next pull cards to give out the gameDeck
            // any player that isn't max gets canDraw = false
            for (int i = 0; i < gameDeck.size(); i++) {
                if(!highestCards.contains(gameDeck.get(i))) {
                    gameDeck.get(i).owner.setCanDraw(false);
                } else {
                    result += gameDeck.get(i).owner + ", ";
//                    System.out.println("Multiple winners :" + gameDeck.get(i).owner);
                }
            }
            
            result = result.substring(0, result.length() - 2) + " vytáhli nejvyšší karty a budou táhnout znova.";
            
            // all cards in gameDeck move to deck (Reusing an array here, small optimalisation)
            tmpSize = gameDeck.size();
            for (int i = 0; i < tmpSize; i++) {
                deck.add(gameDeck.remove(0).card);
            }
        }
        
        propertyChangeSupport.firePropertyChange("drawResult", "", result);
        
        if(PlayerManager.onlyOnePlayerHasCards()) {
            propertyChangeSupport.firePropertyChange("gameFinish", "", PlayerManager.getFirstPlayerWithCards().toString());
        }
        
    }
    
    // <editor-fold desc="Game setup">
    private void generateDeck() {
        deck.clear();
        for (int i = 0; i < 32; i++) {
            int suit = i / 8;
            Suit s = getSuit(suit);
            
            int value = i % 8;
            Value v = getValue(value);
            
            String path = "src/Cards/" + suit + "" + value + ".png";
//            System.out.println(suit + "" + s + ", " + value + "" + v + ", " + path + ", " + value);
            deck.add(new Card(value, s, v, path));
        }
    }
    
    private void dealDeck() {
        if(PlayerManager.getPlayers().isEmpty()) {
            return;
        }
        
        // Remove the cards that can't be evenly given out
        int cardsToRemove = deck.size() % PlayerManager.getPlayers().size();
        for (int i = 0; i < cardsToRemove; i++) {
            deck.remove(0);
        }
        
        int playerCounter = 0;
        while(deck.size() > 0) {
            PlayerManager.getPlayers().get(playerCounter).addCard(deck.remove(0));
            playerCounter += (playerCounter >= PlayerManager.getPlayers().size() - 1) ? - (PlayerManager.getPlayers().size() - 1) : 1;
        }
    }
    
    private void scrambleDeck(int iterations) {
        int ra;
        int rb;
        Card tmp;
        for (int i = 0; i < iterations; i++) {
            ra = r.nextInt(deck.size());
            rb = r.nextInt(deck.size());
            tmp = deck.get(rb);
            deck.set(rb, deck.get(ra));
            deck.set(ra, tmp);
        }
    }
    
    private Suit getSuit(int suit) {
        Suit s;
        switch (suit) {
            case 0:
                s = Suit.HEARTS;
                break;
            case 1:
                s = Suit.BELLS;
                break;
            case 2:
                s = Suit.LEAVES;
                break;
            case 3:
                s = Suit.ACORNS;
                break;
            default:
                throw new AssertionError();
        }
        return s;
    }

    private Value getValue(int val) {
        Value v;
        switch (val) {
            case 0:
                v = Value.LOW;
                break;
            case 1:
                v = Value.HIGH;
                break;
            case 2:
                v = Value.KING;
                break;
            case 3:
                v = Value.SEVEN;
                break;
            case 4:
                v = Value.EIGHT;
                break;
            case 5:
                v = Value.NINE;
                break;
            case 6:
                v = Value.TEN;
                break;
            case 7:
                v = Value.ACE;
                break;
            default:
                throw new AssertionError();
        }
        return v;
    }
    // </editor-fold>
}

class drawnCard {
    Player owner;
    Card card;

    public drawnCard(Player owner, Card card) {
        this.owner = owner;
        this.card = card;
    }
}
