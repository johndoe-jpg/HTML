package sibenice;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Vykreslovani extends JPanel {

    // Vlastnosti
    private ArrayList<BufferedImage> obrazky = new ArrayList<>();
    private int aktualniObrazek;
    private KonecHryEvent khe;

    public Vykreslovani(KonecHryEvent konecHryEvent) {

        this.obrazky = new ArrayList<>();
        this.khe = konecHryEvent;

        String[] soubory = new File("src/obrazky/").list();
        try {
            for (int i = 0; i < soubory.length; i++) {

                this.obrazky.add(ImageIO.read(new File("src/obrazky/" + i + ".png")));

            }
        } catch (IOException ex) {
           
        }
        this.novaHra();
    }

    public Vykreslovani() {

    }

    // Metody
    public void vykresliDalsiObrazek() {
        if (this.aktualniObrazek < this.obrazky.size() - 2) {
            this.aktualniObrazek++;
            this.repaint();
        } else if(this.aktualniObrazek == this.obrazky.size() - 2){
            // událost
            aktualniObrazek++;
            this.repaint();
                khe.fireKonecHryEvent(false);
        }

    }

    public void novaHra() {
        this.aktualniObrazek = 0;
        this.repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);
        if (obrazky.size() > 1) {
            g.setColor(Color.white);
            g.drawImage(this.obrazky.get(this.aktualniObrazek), 0, 0, this);
        }

    }
}
