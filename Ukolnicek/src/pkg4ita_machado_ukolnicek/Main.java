package pkg4ita_machado_ukolnicek;

import java.io.IOException;

/**
 *
 * @author THUND
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        W_Add wa = new W_Add();
        W_View wv = new W_View();
        try {
            new FileManager();
        } catch (IOException ex) {
//            System.out.println(ex);
        }
        
        wa.addPropertyChangeListener(wv);
        wv.addPropertyChangeListener(wa);
        
        wa.setVisible(true);
    }
}
