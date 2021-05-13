package sibenice;

import java.awt.Graphics;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import static sibenice.Sibenice.f;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.time.Clock;
import java.util.ArrayList;

public class Okno extends javax.swing.JFrame implements KonecHryListener {

    private KonecHryEvent konecHryEvent = new KonecHryEvent();
    private String slovo = "";
    private String hadaneSlovo = "";
    private SpravaSlov spravaslov;
    

    public Okno() throws IOException  {
        initComponents();
        spravaslov = new SpravaSlov("src\\slova.txt");
        nactiSlovo();
        
        
        konecHryEvent.addListener(this);
        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                pismeno(e.getKeyChar());
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
        setFocusable(true);
        requestFocusInWindow();
        

    }
    
    private void nactiSlovo(){
        hadaneSlovo = spravaslov.VyberNahodnehoSlova();
        slovo = "";
        for (int i = 0; i < hadaneSlovo.length(); i++) {
            slovo += "-";
        }
        //System.out.println(hadaneSlovo);
        
        jLabel1.setText(slovo);
       
    }
    
     private ArrayList<Integer> zjistiJestliObsahuje(char pismeno){
        ArrayList<Integer> pozice = new ArrayList<>();
        
        
        for (int i = 0; i < hadaneSlovo.length(); i++) {
            if (hadaneSlovo.charAt(i) == pismeno) {
                pozice.add(i);
            }
        }
        return pozice;
   }

    public void pismeno(char key) {
        ArrayList<Integer> pozice = zjistiJestliObsahuje(key);
        
        if(pozice.size() == 0){
            
            
            vykreslovani1.vykresliDalsiObrazek();
        }
        else{
    for (Integer p : pozice) {
            System.out.println(pozice);
            StringBuilder sbSlovo = new StringBuilder(slovo);
            sbSlovo.setCharAt(p, key);
            slovo = sbSlovo.toString();
            jLabel1.setText(slovo);
            
        }
    if(!slovo.contains("-")){
                konecHryEvent.fireKonecHryEvent(true);
            }
        }
    

  }

    @Override
    public void paintComponents(Graphics g) {
        super.paintComponents(g); //To change body of generated methods, choose Tools | Templates.
    }
    
  
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        vykreslovani1 = new sibenice.Vykreslovani(konecHryEvent);
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Šibenice");
        setLocation(new java.awt.Point(400, 100));
        setPreferredSize(new java.awt.Dimension(700, 500));
        setResizable(false);

        jButton1.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 0, 13)); // NOI18N
        jButton1.setText("Nová hra");
        jButton1.setFocusable(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Impact", 1, 36)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout vykreslovani1Layout = new javax.swing.GroupLayout(vykreslovani1);
        vykreslovani1.setLayout(vykreslovani1Layout);
        vykreslovani1Layout.setHorizontalGroup(
            vykreslovani1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, vykreslovani1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 382, Short.MAX_VALUE)
                .addContainerGap())
        );
        vykreslovani1Layout.setVerticalGroup(
            vykreslovani1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, vykreslovani1Layout.createSequentialGroup()
                .addContainerGap(360, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(vykreslovani1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 172, Short.MAX_VALUE)
                .addGap(7, 7, 7))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(vykreslovani1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(104, 104, 104)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        vykreslovani1.novaHra();
        nactiSlovo();
        

    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private sibenice.Vykreslovani vykreslovani1;
    // End of variables declaration//GEN-END:variables

    @Override
    public void konecHry(boolean vyhra) {
        
        System.out.println("Konec");

        JDialog d = new JDialog(f, "Konec hry!");
        JLabel l;
        if(vyhra){
            l = new JLabel("Gratuluji! Vyhrál jsi! ");
        }else {
            l = new JLabel("Jseš oběšen!");


        }
        
        
        d.add(l);
        d.setSize(350, 350);
        d.setLocation(550, 200);
        d.setVisible(true);
        
        
        

    }
}
