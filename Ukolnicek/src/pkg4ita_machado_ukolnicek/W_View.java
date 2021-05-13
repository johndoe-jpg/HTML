package pkg4ita_machado_ukolnicek;

import java.awt.Dimension;
import java.awt.Point;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 *
 * @author THUND
 */
public class W_View extends javax.swing.JFrame implements PropertyChangeListener {

    private DateTimeFormatter dateForm = DateTimeFormatter.ofPattern("dd.MM.uu");
    private DateTimeFormatter timeForm = DateTimeFormatter.ofPattern("kk:mm:ss");
    private ArrayList<JPanel> panels = new ArrayList<>();

    /**
     * Creates new form W_view
     */
    public W_View() {
        initComponents();
        jScrollPane1.getVerticalScrollBar().setUnitIncrement(16);
    }

    private void updateNotes() {
        scrollingPanel.removeAll();

        panels.clear();
        ArrayList<Note> notes = null;

        try {
            notes = FileManager.GetNotes();
        } catch (FileNotFoundException ex) {
//            System.out.println(ex);
        }

        if (notes == null || notes.isEmpty()) {
            return;
        }

        int offset = 0;

        for (Note note : notes) {
            JPanel note_panel = new javax.swing.JPanel();
            JLabel title = new javax.swing.JLabel();
            JScrollPane jScrollPane12 = new javax.swing.JScrollPane();
            JTextArea body = new javax.swing.JTextArea();
            JLabel timestamp = new javax.swing.JLabel();
            JButton delete = new javax.swing.JButton();

//            jButton1.setText("<html>Přidat<br>poznámku</html>");
//            jButton1.addActionListener(new java.awt.event.ActionListener() {
//                public void actionPerformed(java.awt.event.ActionEvent evt) {
//                    jButton1ActionPerformed(evt);
//                }
//            });
            note_panel.setBounds(20, 70 + offset, 500, 200);
            note_panel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(95, 245, 95), 3));

            offset += note_panel.getHeight() + 20;

            title.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
            title.setText(note.title);
            title.setFocusable(false);

            body.setColumns(20);
            body.setLineWrap(true);
            body.setRows(5);
            body.setText(note.body);
            body.setDisabledTextColor(new java.awt.Color(0, 0, 0));
            body.setEnabled(false);
            body.setFocusable(false);
            jScrollPane12.setViewportView(body);
            jScrollPane12.setFocusable(false);

            LocalDateTime dt = LocalDateTime.parse(note.timestamp);
            String date = dt.format(dateForm);
            String time = dt.format(timeForm);

            timestamp.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
            timestamp.setText("<html>" + date + "<br>" + time + "</html>");
            timestamp.setFocusable(false);

            delete.setText("X");
            delete.setFocusable(false);
            delete.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    FileManager.removeLineById(note.id);
                    updateNotes();
                    if (panels.isEmpty()) {
                        scrollingPanel.repaint();
                        scrollingPanel.revalidate();
                        jScrollPane1.repaint();
                        repaint();
                    }
                }
            });

            javax.swing.GroupLayout note_panelLayout = new javax.swing.GroupLayout(note_panel);
            note_panel.setLayout(note_panelLayout);
            note_panelLayout.setHorizontalGroup(
                    note_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(note_panelLayout.createSequentialGroup()
                                    .addContainerGap()
                                    .addGroup(note_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jScrollPane12)
                                            .addGroup(note_panelLayout.createSequentialGroup()
                                                    .addComponent(title, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(timestamp, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(delete, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addContainerGap())
            );
            note_panelLayout.setVerticalGroup(
                    note_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(note_panelLayout.createSequentialGroup()
                                    .addContainerGap()
                                    .addGroup(note_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(title, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                                            .addComponent(timestamp)
                                            .addComponent(delete, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jScrollPane12, javax.swing.GroupLayout.DEFAULT_SIZE, 114, Short.MAX_VALUE)
                                    .addContainerGap())
            );

            panels.add(note_panel);
            scrollingPanel.add(note_panel);
            scrollingPanel.repaint();
            scrollingPanel.revalidate();
            jScrollPane1.repaint();
            repaint();
        }
        repaint();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        scrollingPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Poznámky");
        setResizable(false);

        jButton1.setText("<html>Přidat<br>poznámku</html>");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        scrollingPanel.setLayout(new javax.swing.BoxLayout(scrollingPanel, javax.swing.BoxLayout.Y_AXIS));
        jScrollPane1.setViewportView(scrollingPanel);

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        jLabel1.setText("Poznámky");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 488, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 720, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.setVisible(false);
        firePropertyChange("addWindow", null, getLocation());
    }//GEN-LAST:event_jButton1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel scrollingPanel;
    // End of variables declaration//GEN-END:variables

    @Override
    public void propertyChange(PropertyChangeEvent pce) {
        if (pce.getPropertyName().equals("viewWindow")) {
            this.setLocation((Point) pce.getNewValue());
            updateNotes();
            this.setVisible(true);
        }
    }
}
