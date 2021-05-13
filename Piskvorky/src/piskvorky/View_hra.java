
package piskvorky;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.border.LineBorder;


public class View_hra extends javax.swing.JFrame {

    private static View_hra instance_view=null;
    private View_hra() {
        initComponents();
        this.setBounds(0, 0, 800, 850);
        this.setResizable(false);
        Vytvor_Pole();
    }
    
    public static View_hra getInstance() 
    { 
        if (instance_view == null) 
            instance_view = new View_hra(); 
  
        return instance_view; 
    } 
    Hra hra = Hra.getInstance();
    public JButton[] buttons = new JButton[hra.x*hra.y];
    public int ButtCount;
    
    public void Vytvor_Pole(){
        int xin = 0;
        int yin = 0;
        
        buttons = new JButton[hra.x*hra.y];
        ButtCount = 0;
        
        for (int i = 0; i < hra.x; i++) {
            for (int j = 0; j < hra.y; j++) {
                generateButton(xin, yin, i, j);
                xin++;
                ButtCount++;
            }
            xin = 0;
                yin++;
        }
    }
    
    private void generateButton(int xGrid, int yGrid, int x, int y) {
        JButton btn = new JButton();
        btn.setText("");
        btn.setFont(new Font("MV Boli",Font.BOLD, 25));
        btn.setBounds(10 + (40 * xGrid), 320 + (40 * yGrid), 32, 32);
        btn.setBorder(new LineBorder(Color.black));

        btn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btn.setText(hra.curr_symbol);
                hra.vnitrni_pole[x][y] = hra.curr_player;
                hra.JePet();
                if (hra.curr_player==1) {
                    hra.curr_player=2;
                    hra.curr_symbol=hra.player2;
                    jLabel1.setText("Hraje: "+hra.player2);
                }else{
                    hra.curr_player=1;
                    hra.curr_symbol=hra.player1;
                    jLabel1.setText("Hraje: "+hra.player1);
                }
                btn.setEnabled(false);
            }//action performed
        });//action listener
        buttons[ButtCount]=btn;
        this.add(btn);
        revalidate();
        repaint();
    }
    
    public void SmazStary(){       
            for (int i = 0; i < buttons.length; i++) {
            this.remove(buttons[i]);
        }     
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("MV Boli", 1, 40)); // NOI18N
        jLabel1.setText("Hraje: x");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(102, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(234, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(View_hra.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(View_hra.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(View_hra.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(View_hra.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new View_hra().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
