
package sibenice;

import java.util.List;
import java.util.ArrayList;


public class KonecHryEvent {
    
    private List<KonecHryListener> listeners = new ArrayList<KonecHryListener>();

    public void addListener(KonecHryListener toAdd) {
        listeners.add(toAdd);
    }

    public void fireKonecHryEvent(boolean vyhra) {
      

        
        for (KonecHryListener khl : listeners)
            khl.konecHry(vyhra);
    }
}


