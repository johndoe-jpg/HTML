package kostky;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class KostkaWidget extends JPanel implements Runnable{

    private int cislo;
    private final PropertyChangeSupport pcs;
    private ArrayList<BufferedImage> obrazky;
    private volatile boolean hod = false;
    private Random r = new Random();
    
    
    public KostkaWidget(){
        this.pcs = new PropertyChangeSupport( this );
        this.obrazky = new ArrayList();
        
        for(int i = 1; i <= 6; i++){
            try {
                this.obrazky.add( ImageIO.read( new File( "src/obrazky/" + i + ".png" ) ) );
            } catch (IOException ex) {
                System.out.println("CHYBA! Obrázky neexistují");
            }
        }
    }
    
    // Public API
    public void hazej(){
        if(this.hod){
            System.out.println("Právě probíha hod");
        }else{
            this.hod = true;
        }
    }
    
    
    // Threads
    @Override
    public void run() {
        while(true){
            if(this.hod){
                this.hodKostkou();
                this.hod = false;
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                Logger.getLogger(KostkaWidget.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    // Drawing
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponents(g); //To change body of generated methods, choose Tools | Templates.
        this.clearG(g);
        if( this.cislo != 0 ){
            g.drawImage( this.obrazky.get(this.cislo-1), 0, 0, 150, 120, this );
        }
        
    }
    
    private void clearG(Graphics g){
        g.setColor(Color.white);
        g.fillRect(0, 0, (int) this.getSize().getWidth(), (int) this.getSize().getHeight());
        g.setColor(Color.black);
    }
    
    // Utils
    private int nahodneCislo(int min, int max){
        return this.r.nextInt(max - min + 1) + min;
    }
    
    private void hodKostkou(){
        long sleep = 100; // 100 milis
        int cislo;
        for(int i = 0; i <= (int) (5 + this.nahodneCislo(0, 5)); i++){
            // ziskat cislo a vykreslit
            cislo = this.nahodneCislo(1, 6);
            this.cislo = cislo;
            this.repaint();
           
            // uspat
            try {
                Thread.sleep(sleep);
            } catch (InterruptedException ex) {
                Logger.getLogger(KostkaWidget.class.getName()).log(Level.SEVERE, null, ex);
            }
            sleep = (long) (sleep * 1.2);  
        }
        this.pcs.firePropertyChange("Statistika", null, String.valueOf(this.cislo));
        this.pcs.firePropertyChange("Statistika Celkova", null, String.valueOf(this.cislo));
    }
    
    public void pridejListener( PropertyChangeListener l )
    {
        this.pcs.addPropertyChangeListener( l );
    }
    
    public void odeberListener( PropertyChangeListener l )
    {
        this.pcs.removePropertyChangeListener( l );
    }
}
