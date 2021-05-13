package vetsi_bere;

/**
 *
 * @author THUND
 */
public class Vetsi_bere {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
//        PlayerManager.addPlayer("a");
//        PlayerManager.addPlayer("b");
//        PlayerManager.addPlayer("c");
//        PlayerManager.addPlayer("d");
//        PlayerManager.addPlayer("e");
//        PlayerManager.addPlayer("f");
//        PlayerManager.addPlayer("g");
//        PlayerManager.addPlayer("h");
        MainMenu mm = new MainMenu();
        mm.setVisible(true);
        Game g = new Game();
        
        mm.addPropertyChangeListener(g);
        g.addPropertyChangeListener(mm);
    }
}
