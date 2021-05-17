package hledanimin;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JPanel;


public class HerniPanel extends JPanel{
    
    private Hra hra;
    int vel = 20;
    
    public HerniPanel() {
        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent me) {
                int x = (int) me.getX() / vel;
                int y = (int) me.getY() / vel;
                hra.klik(y, x);
                repaint();
            }

            @Override
            public void mousePressed(MouseEvent me) {
                
            }

            @Override
            public void mouseReleased(MouseEvent me) {
                
            }

            @Override
            public void mouseEntered(MouseEvent me) {
               
            }

            @Override
            public void mouseExited(MouseEvent me) {
               
            }
        });
    }
    
    public void set_hra(Hra hra) {
        this.hra = hra;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        for (int i = 0; i < this.hra.velikost(); i++){
            for (int j = 0; j < this.hra.velikost(); j++){
                g.setColor(Color.black);
                g.drawRect(vel * i, vel * j, vel, vel);
                g.setColor(Color.CYAN);
                g.fillRect(vel * i + 1, vel * j + 1, vel - 1, vel - 1);
                
                if (this.hra.getPole_view()[j][i] == -2 || this.hra.getPole_view()[j][i] == -3)
                g.setColor(Color.black);
                g.drawString(String.valueOf( this.hra.getPole()[j][i] ), vel * i + vel / 4, vel * (j+1) - vel/4);
            }
        }
    }
   
}
