/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg4ita_machado_2048;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

/**
 *
 * @author THUND
 */
public class Graphics extends javax.swing.JPanel {
    
    public int[][] board;
    private Color light = new Color(0xf2f068);
    private Color dark = new Color(0xf28968);

    /**
     * Creates new form Graphics
     */
    public Graphics() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
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
            .addGap(0, 400, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    @Override
    protected void paintComponent(java.awt.Graphics g) {
        if(board == null || board.length == 0 || board[0].length == 0) {
            return;
        }
        
        super.paintComponent(g);
        
        g.setColor(light);
        g.fillRect(0, 0, getWidth(), getHeight());
        
        int s = 0;
        int la = board.length;
        int lb = board[0].length;
        if(la > lb) {
            s = getWidth() / la;
        } else {
            s = getWidth() / lb;
        }
        int f = s;
        g.setFont(new Font("Verdana", 0, f));
        for (int i = 0; i < la; i++) {
            for (int j = 0; j < lb; j++) {
                g.setColor(dark);
                g.drawRect(i * s, j * s, s, s);
                if(board[i][j] == 0) {
                    continue;
                }
                g.setFont(new Font("Verdana", 0, f));
                if(board[i][j] > 9) {
                    g.setFont(new Font("Verdana", 0,(int) (f / 1.5)));
                }
                if(board[i][j] > 99) {
                    g.setFont(new Font("Verdana", 0, f / 2));
                }
                if(board[i][j] > 999) {
                    g.setFont(new Font("Verdana", 0,(int) (f / 3)));
                }
                g.setColor(Color.black);
                g.drawString("" + board[i][j], i * s + s / 2 - f / 2, j * s + s / 2 + f / 2);
            }
        }
    }

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}