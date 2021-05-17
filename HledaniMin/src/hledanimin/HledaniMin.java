package hledanimin;

public class HledaniMin {

    public static void main(String[] args) {       
        Hra hra = new Hra(7, 0.05);
        
        new HerniOkno(hra).setVisible(true);
        hra.vypis_pole();
        //System.out.println( hra.pocet_sousednich_min(4, 0) );
        //hra.vypis_pole_sousedi();
        
    }
    
}
