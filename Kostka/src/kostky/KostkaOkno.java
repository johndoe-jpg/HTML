package kostky;

import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

public class KostkaOkno extends javax.swing.JFrame implements PropertyChangeListener, ComponentListener{

    private final boolean control;
    private ArrayList<KostkaOkno> okna;
    private ArrayList<StatistikaOkno> statistiky;
    private ArrayList<String> freedStatistiky;
    private final PropertyChangeSupport pcs;
    public KostkaOkno(boolean control) {
        initComponents();
        
        this.control = control;
        this.minusButton.setVisible(control);
        this.plusButton.setVisible(control);
        this.hodButton.setVisible(control);
        this.modeJednaButton.setVisible(control);
        this.modeDvaButton.setVisible(control);
        
        this.okna = new ArrayList();
        this.statistiky = new ArrayList();
        this.freedStatistiky = new ArrayList();
        if(control){
            this.pcs = new PropertyChangeSupport( this );
        }else{
            this.pcs = null;
        }
        
        this.addComponentListener(this);
        
        Thread kostkaThread = new Thread(this.kostkaWidget1);
        kostkaThread.start();
    }
    
    @Override
    public synchronized boolean isVisible(){
        return super.isVisible();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        kostkaWidget1 = new kostky.KostkaWidget();
        hodButton = new javax.swing.JButton();
        plusButton = new javax.swing.JButton();
        minusButton = new javax.swing.JButton();
        modeJednaButton = new javax.swing.JButton();
        modeDvaButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setAlwaysOnTop(true);
        setResizable(false);

        javax.swing.GroupLayout kostkaWidget1Layout = new javax.swing.GroupLayout(kostkaWidget1);
        kostkaWidget1.setLayout(kostkaWidget1Layout);
        kostkaWidget1Layout.setHorizontalGroup(
            kostkaWidget1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        kostkaWidget1Layout.setVerticalGroup(
            kostkaWidget1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 120, Short.MAX_VALUE)
        );

        hodButton.setBackground(new java.awt.Color(204, 204, 204));
        hodButton.setFont(new java.awt.Font("Yu Gothic", 0, 14)); // NOI18N
        hodButton.setForeground(new java.awt.Color(255, 0, 0));
        hodButton.setText("Ho?? kostkou");
        hodButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hodButtonActionPerformed(evt);
            }
        });

        plusButton.setText("+");
        plusButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                plusButtonActionPerformed(evt);
            }
        });

        minusButton.setText("-");
        minusButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                minusButtonActionPerformed(evt);
            }
        });

        modeJednaButton.setText("Statistika");
        modeJednaButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modeJednaButtonActionPerformed(evt);
            }
        });

        modeDvaButton.setText("sta. celkov??");
        modeDvaButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modeDvaButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(hodButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(kostkaWidget1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(modeJednaButton, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 61, Short.MAX_VALUE)
                        .addComponent(modeDvaButton))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(plusButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(minusButton, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(kostkaWidget1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(48, 48, 48)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(plusButton, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(minusButton, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(modeJednaButton)
                    .addComponent(modeDvaButton, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(hodButton, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void hodButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hodButtonActionPerformed
        // TODO add your handling code here:
        this.pcs.firePropertyChange("statistika_reset", null, true);
        this.pcs.firePropertyChange("kostka_hod", null, true);
        this.kostkaWidget1.hazej();
    }//GEN-LAST:event_hodButtonActionPerformed

    private void plusButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_plusButtonActionPerformed
        // TODO add your handling code here:
        if(this.okna.size() < 5){
            KostkaOkno k = new KostkaOkno(false);
            k.setVisible(true);
            this.okna.add(k);
            
            // Component listener
            k.addComponentListener(this);
            
            // Listener pro hod
            this.pridejListener(k);
            
            // Listener pro statistiku
            for(StatistikaOkno s : this.statistiky){
                k.pridejStatistikaListener(s);
            }
        }
    }//GEN-LAST:event_plusButtonActionPerformed

    private void minusButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_minusButtonActionPerformed
        // TODO add your handling code here:
        if(this.okna.size() > 0){
            KostkaOkno k = this.okna.get(this.okna.size() - 1);
            this.okna.remove(k);
            
            // Component listener
            k.removeComponentListener(this);
            
            // Odebrat listener pro hod
            this.odeberListener(k);
            
            // Odebrat listener pro statistiku
            for(StatistikaOkno s : this.statistiky){
                k.odeberStatistikaListener(s);
            }
            k.setVisible(false);
            k.dispose();  
        }
        
    }//GEN-LAST:event_minusButtonActionPerformed

    private void modeJednaButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modeJednaButtonActionPerformed
        // TODO add your handling code here:
        this.modeOne();    
    }//GEN-LAST:event_modeJednaButtonActionPerformed

    private void modeDvaButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modeDvaButtonActionPerformed
        // TODO add your handling code here:
        this.modeTwo();
    }//GEN-LAST:event_modeDvaButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton hodButton;
    private kostky.KostkaWidget kostkaWidget1;
    private javax.swing.JButton minusButton;
    private javax.swing.JButton modeDvaButton;
    private javax.swing.JButton modeJednaButton;
    private javax.swing.JButton plusButton;
    // End of variables declaration//GEN-END:variables

    @Override
    public void propertyChange(PropertyChangeEvent pce) {
        if(!this.control){
            if ( pce.getPropertyName().equals( "kostka_hod" ) )
            {
                this.kostkaWidget1.hazej();
            }
        }else{
            if ( pce.getPropertyName().equals( "free" ) )
            {
                this.freedStatistiky.add( (String) pce.getNewValue() );
            }
            if ( pce.getPropertyName().equals( "lock" ) )
            {
                this.freedStatistiky.remove( (String) pce.getNewValue() );
            }
        }
    }
    
    private void modeOne(){
        for(StatistikaOkno s : this.statistiky){
            if(s.getID().equals("Statistika Celkova")){
                s.removeComponentListener(this);
                this.okna.forEach((k) -> {
                    k.odeberStatistikaListener(s);
                });
                this.statistiky.remove(s);
                s.setVisible(false);
                s.dispose();
                return;
            }
        }
        
        for(StatistikaOkno s : this.statistiky){
            if(s.getID().equals("Statistika")){
                return;
            }
        }
        
        StatistikaOkno s = new StatistikaOkno("Statistika", true, true);
        s.setVisible(true);
        this.statistiky.add(s);
        s.pridejListener(this);
        s.addComponentListener(this);
        this.pridejListener(s);
        this.pridejStatistikaListener(s);
        this.okna.forEach((k) -> {
            k.pridejStatistikaListener(s);
        });
    }
    
    private void modeTwo(){
        this.modeOne();
        StatistikaOkno s = new StatistikaOkno("Statistika Celkova", false, true);
        s.setVisible(true);
        this.statistiky.add(s);
        s.pridejListener(this);
        s.addComponentListener(this);
        this.pridejStatistikaListener(s);
        this.okna.forEach((k) -> {
            k.pridejStatistikaListener(s);
        });
    }
    
    public void pridejListener( PropertyChangeListener l )
    {
        this.pcs.addPropertyChangeListener( l );
    }
    
    public void pridejStatistikaListener( PropertyChangeListener l )
    {
        this.kostkaWidget1.pridejListener( l );
    }

    public void odeberListener( PropertyChangeListener l )
    {
        this.pcs.removePropertyChangeListener( l );
    }
    
    public void odeberStatistikaListener( PropertyChangeListener l )
    {
        this.kostkaWidget1.odeberListener( l );
    }

    @Override
    public void componentResized(ComponentEvent ce) {
    }

    @Override
    public void componentMoved(ComponentEvent ce) {
        // Sort kostky
        if(this.okna.size() > 0){
            int x = (int) this.getLocation().getX();
            int y = (int) this.getLocation().getY();
            for(int i = 0; i < this.okna.size(); i++){
                KostkaOkno k = this.okna.get(i);
                k.setLocation(x - k.getWidth(), y);
                x = (int) k.getLocation().getX();
                y = (int) k.getLocation().getY();
            }
        }
        
        if(this.statistiky.size() > 0){
            int x = (int) this.getLocation().getX();
            int y = (int) this.getLocation().getY();
            for(int i = 0; i < this.statistiky.size(); i++){
                StatistikaOkno s = this.statistiky.get(i);
                if(this.freedStatistiky.contains(s.getID())){
                    continue;
                }
                s.setLocation(x + s.getWidth(), y);
                x = (int) s.getLocation().getX();
                y = (int) s.getLocation().getY();
            }
        }
    }

    @Override
    public void componentShown(ComponentEvent ce) {
    }

    @Override
    public void componentHidden(ComponentEvent ce) {
    }
}
