
package pexeso;

import java.awt.Dimension;
import javax.swing.JFrame;


public class Pexeso{
    public static void main(String[] args){
        Hra hra = new Hra();
        hra.setPreferredSize(new Dimension(500,500));
        hra.setLocation(500, 150);
        hra.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        hra.pack();
        hra.setVisible(true);
    }   
}
