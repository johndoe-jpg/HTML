package vetsi_bere;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

/**
 *
 * @author THUND
 */
public class Card {

    private int internalValue;
    private Suit suit;
    private Value value;
    private String imgPath;

    public Card(int internalValue, Suit suit, Value value, String imgPath) {
        this.internalValue = internalValue;
        this.suit = suit;
        this.value = value;
        this.imgPath = imgPath;
    }

    /**
     * 
     * @param g
     * @param pos
     * @param height
     */
    public void drawCard(Graphics g, Point pos, int height) {
        BufferedImage bi = null;
        int h = height;

        try {
            bi = ImageIO.read(new File(imgPath));

            float scale = (float) bi.getWidth() / (float) bi.getHeight();
            int w = (int) ((float) h * scale);
            g.drawImage(bi, pos.x, pos.y, w, h, null);

        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public int getSysValue() {
        return internalValue;
    }

    @Override
    public String toString() {
        return suit + ", " + value + " (" + internalValue + ")";
    }
}
