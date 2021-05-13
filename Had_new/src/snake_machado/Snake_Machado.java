package snake_machado;

/**
 *
 * @author THUND
 */
public class Snake_Machado {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Menu m = new Menu();
        GameWindow gw = new GameWindow();
        m.addPropertyChangeListener(gw);
        gw.addPropertyChangeListener(m);
    }
}
