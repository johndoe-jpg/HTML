
package pexeso;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import pexeso.Model;



@SuppressWarnings("serial")
public class Hra extends JFrame{


    private List<Model> models;
    private Model selectedCard;
    private Model c1;
    private Model c2;
    private Timer t;

    public Hra(){

        int pairs = 2;
        List<Model> cardsList = new ArrayList<Model>();
        List<Integer> cardVals = new ArrayList<Integer>();

        for (int i = 0; i < pairs; i++){
            cardVals.add(i);
            cardVals.add(i);
        }
        Collections.shuffle(cardVals);

        for (int val : cardVals){
            Model m = new Model() {};
            
            m.setId(val);
            m.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent ae){
                    selectedCard = m;
                    doTurn();
                }
            });
            cardsList.add(m);
        }
        this.models = cardsList;
        
        t = new Timer(750, new ActionListener(){
            public void actionPerformed(ActionEvent ae){
                checkCards();
            }
        });

        t.setRepeats(false);

        
        Container pane = getContentPane();
        pane.setLayout(new GridLayout(4, 5));
        for (Model c : models){
            pane.add(c);
        }
        setTitle("Pexeso");
    }

    public void doTurn(){
        if (c1 == null && c2 == null){
            c1 = selectedCard;
            c1.setText(String.valueOf(c1.getId()));
        }

        if (c1 != null && c1 != selectedCard && c2 == null){
            c2 = selectedCard;
            c2.setText(String.valueOf(c2.getId()));
            t.start();

        }
    }

    public void checkCards(){
        if (c1.getId() == c2.getId()){
            c2.setEnabled(false);
            c1.setEnabled(false);
            c1.setMatched(true);
            c2.setMatched(true);
            if (this.isGameWon()){
                JOptionPane.showMessageDialog(this, "Vyhrál jsi!");
                System.exit(0);
            }
        }

        else{
            c1.setText("");
            c2.setText("");
        }
        c1 = null; 
        c2 = null;
    }

    public boolean isGameWon(){
        for(Model m: this.models){
            if (m.getMatched() == false){
                return false;
            }
        }
        return true;
    }

}