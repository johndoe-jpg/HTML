package pkg4ita_machado_2048;

/**
 *
 * @author THUND
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        W_menu m = new W_menu();
        W_game g = new W_game();
        
        m.addPropertyChangeListener(g);
        g.addPropertyChangeListener(m);
    }
}
