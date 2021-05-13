
package pexeso;

import java.awt.Graphics;
import javax.swing.JPanel;


public class Platno extends JPanel {
    //Vlastnosti
    private GameController controller;
    
    
    //Konstruktor
    
    public Platno()
    {
        this.controller = new GameController();
    }
    //Rozhraní
    
    @Override
    public void paintComponent(Graphics g)
    {
        super.paint(g);
    }
}
