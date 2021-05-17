package had;

import java.awt.Color;
import java.awt.Graphics;

public class Platno extends javax.swing.JPanel {

    private Model m = Model.getInstance();

    public Platno() {
        initComponents();
        m.addPropertyChangeListener((evt) -> {
            repaint();
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (m.getX() == 0 || m.getY() == 0) {
            return;
        }

        double x = (double) getWidth() / m.getX();
        double y = (double) getHeight() / m.getY();

        for (Position p : m.getSnake().getTelo()) {
            g.setColor(new Color(180, 255, 100));
            g.fillRect((int) (x * p.x), (int) (y * p.y), (int) x, (int) y);
            g.setColor(Color.BLACK);
            g.drawRect((int) (x * p.x), (int) (y * p.y), (int) x, (int) y);
        }

        Position j = m.getJablicko();
        g.setColor(Color.red);
        g.fillRect((int) (x * j.x), (int) (y * j.y), (int) x, (int) y);

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
