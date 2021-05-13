package sibenice;

import java.io.IOException;
import javax.swing.JFrame;

public class Sibenice {

    
     static JFrame f; 
    public static void main(String[] args) throws IOException {

        Okno okno = new Okno();
        okno.setVisible(true);
        
        f = new JFrame("frame"); 
        

    }

}
