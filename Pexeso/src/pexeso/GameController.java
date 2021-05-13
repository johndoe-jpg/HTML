
package pexeso;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.Random;


public class GameController {
    //Vlastnosti
    private int[] policka;
    
    //Konstruktor
    public GameController()
    {
        this.policka = new int [4 * 4];
        this.naplPole();
        this.zamichatPole();
        this.novaHra();
    }
    //Metody
    private void naplPole()
    {
        for (int i = 0; i < this.policka.length; i+= 2)
        {
            this.policka[i] = this.policka[i + 1] = i / 2;
        }
    }
    
    private void zamichatPole()
    {
       Random random = new Random();
       for (int i = this.policka.length - 1; i > 0; i--)
       {
           int index = random.nextInt(i + 1);
           int temp = this.policka[index];
           this.policka[index] = this.policka[i];
           this.policka[i] = temp;
       }
    }
    
        public void vypisPole()
    {
        for (int i = 0; i < this.policka.length; i++) 
        {
            System.out.println(this.policka[i] + " ");
        }
        System.out.println("");
    }
        
            private boolean vseVyrazeno()
    {
      for (int i = 0; i < this.policka.length; i++)
      {
          if(this.policka[i] != -1)
          {
              return false;
          }
      }
        this.propertyChangeSupport.firePropertyChange("konec", null, true);
        return true;
    }

    //Rozhraní
    private void vyraditKartu(int index)
    {
        this.policka[index] = -1;
    }
   
    
    public boolean porovnaniKaret( int a, int b)
    {
        if (this.policka[a] == this.policka[b])
        {
            this.vyraditKartu(a);
            this.vyraditKartu(b);
            this.propertyChangeSupport.firePropertyChange("porovnani",null, true);
            this.vseVyrazeno();
            return true;
        }
        this.propertyChangeSupport.firePropertyChange("porovnani",null, false);
        return false;
    }
    
    public void novaHra()   
    {
        this.naplPole();
    }
    
    //Událost
        private String string;
    public String getString() {
        return string;
    }

 

    private transient final PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.removePropertyChangeListener(listener);
    }

}
 