package pkg3itazivot;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.Timer;

public class Okno extends javax.swing.JFrame
{
    private Zivot zivot;
    private Panel panel;
    private final Timer timer;
    private final SaveLoad saveLoad;
    private boolean inicializovano;

    public Okno()
    {
        saveLoad = new SaveLoad( "save.txt" );

        initComponents();
        startButton.setVisible( false );
        stopButton.setVisible( false );
        saveButton.setVisible( false );
        if ( !saveLoad.DoesFileExist() )
        {
            loadButton.setEnabled( false );
        }

        ActionListener taskActionListener = ( ActionEvent e ) ->
        {
            Tiknuto();
        };
        timer = new Timer( 1000, taskActionListener );
    }

   
    private void Tiknuto()
    {
        zivot.Zij();
        panel.repaint();
        repaint();
        Aktualizuj();

        if ( !zivot.JeNekdoNaZivu() )
        {
            StopTimer();
            Resetuj();
            JOptionPane.showMessageDialog( this, "Na živu není ani jedna buňka. Toto značí pouze konec hry.", "Konec hry!", JOptionPane.WARNING_MESSAGE );
        }
    }

   
    private void Initializuj() throws Exception
    {
        if ( inicializovano )
        {
            return;
        }

        if ( zivot == null )
        {
            throw new Exception( "Neexistuje instance třídy Zivot!" );
        }

        panel = new Panel( zivot );
        this.add( panel );
        panel.setLocation( 0, 0 );

        saveButton.setVisible( true );

        panel.repaint();
        repaint();

        Aktualizuj();

        inicializovano = true;
    }

    
    private void Initializuj( int count )
    {
        if ( inicializovano )
        {
            return;
        }

        zivot = new Zivot( count );

        try
        {
            Initializuj();
        }
        catch ( Exception exception )
        {
            // Do nothing.
        }
    }


    private void Initializuj( int count, int size )
    {
        if ( inicializovano )
        {
            return;
        }

        zivot = new Zivot( count, size );

        try
        {
            Initializuj();
        }
        catch ( Exception exception )
        {
            // Do nothing.
        }
    }

    
    private void Resetuj()
    {
        if ( inicializovano )
        {
            startButton.setVisible( false );
            stopButton.setVisible( false );
            saveButton.setVisible( false );

            Aktualizuj();

            remove( panel );
            panel = null;
            zivot = null;
            inicializovano = false;
        }
    }

    
    private void Aktualizuj()
    {
        setTitle( Integer.toString( zivot.getDelkaZivota() ) );
    }

    
    private void StartTimer()
    {
        timer.start();
        startButton.setVisible( false );
        stopButton.setVisible( true );
        startGameButton.setVisible( false );
    }

    
    private void StopTimer()
    {
        timer.stop();
        startButton.setVisible( true );
        stopButton.setVisible( false );
        startGameButton.setVisible( true );
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        startButton = new javax.swing.JButton();
        stopButton = new javax.swing.JButton();
        loadButton = new javax.swing.JButton();
        saveButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        lifeCount = new javax.swing.JTextField();
        startGameButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(0, 0, 0));
        setResizable(false);

        startButton.setText("Start");
        startButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startButtonActionPerformed(evt);
            }
        });

        stopButton.setText("Stop");
        stopButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stopButtonActionPerformed(evt);
            }
        });

        loadButton.setText("Load");
        loadButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loadButtonActionPerformed(evt);
            }
        });

        saveButton.setText("Save");
        saveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveButtonActionPerformed(evt);
            }
        });

        jLabel1.setText("Životy:");

        lifeCount.setText("150");

        startGameButton.setText("Začni");
        startGameButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startGameButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(602, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(startButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(loadButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(saveButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lifeCount, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(stopButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(startGameButton))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(startButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(stopButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(loadButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(saveButton)
                .addGap(37, 37, 37)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lifeCount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46)
                .addComponent(startGameButton)
                .addContainerGap(247, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void startButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startButtonActionPerformed
        StartTimer();
    }//GEN-LAST:event_startButtonActionPerformed

    private void stopButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stopButtonActionPerformed
        StopTimer();
    }//GEN-LAST:event_stopButtonActionPerformed

    private void loadButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loadButtonActionPerformed
        StopTimer();

        int[][] zeme = saveLoad.Load();
        Initializuj( 28 );
        zivot.setZeme( zeme );
        panel.repaint();
        repaint();
        Aktualizuj();
    }//GEN-LAST:event_loadButtonActionPerformed

    private void saveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveButtonActionPerformed
        saveLoad.Save( zivot.getZeme() );
        loadButton.setEnabled( true );
    }//GEN-LAST:event_saveButtonActionPerformed

    private void startGameButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_startGameButtonActionPerformed
    {//GEN-HEADEREND:event_startGameButtonActionPerformed
        Resetuj();

        try
        {
            Initializuj( 28, Integer.valueOf( lifeCount.getText() ) );
        }
        catch ( NumberFormatException exception )
        {
            lifeCount.setText( "150" );
        }

        startButton.setVisible( true );
        stopButton.setVisible( false );
    }//GEN-LAST:event_startGameButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JTextField lifeCount;
    private javax.swing.JButton loadButton;
    private javax.swing.JButton saveButton;
    private javax.swing.JButton startButton;
    private javax.swing.JButton startGameButton;
    private javax.swing.JButton stopButton;
    // End of variables declaration//GEN-END:variables
}
