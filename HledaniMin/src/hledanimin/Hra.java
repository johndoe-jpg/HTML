package hledanimin;


public class Hra {
    // -1 -> mina
    private int pole[][];
    private int pole_view[][];
    
    public Hra(int vel, double mines_percent) {
        this.pole = new int[vel][vel];
        this.pole_view = new int[vel][vel];
        this.zaminuj_pole(mines_percent);
        this.vypln_pole_sousedi();
    }
    
    int velikost() {
        return this.pole.length;
    }

    public int[][] getPole() {
        return pole;
    }

    public int[][] getPole_view() {
        return pole_view;
    }
    
    
    
    void zaminuj_pole(double mines_percent) {
        for (int i = 0; i < this.pole.length; i++) {
            for (int j = 0; j < this.pole[0].length; j++) {
                double rand = Math.random();
                if (rand < mines_percent) {
                    this.pole[i][j] = -1;
                }
            }
        }
    }
    
    // Pocet sousedi min
    boolean obsahuje_minu(int i, int j){
        if (this.pole[i][j] == -1){
            return true;
        }
        return false;
    }
    
    int pocet_sousednich_min(int x, int y) {
        int suma = 0;
        
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                if (i == 0 && j == 0)
                    continue;
                int temp_i = i + x;
                int temp_j = j + y;
                
                if (temp_i > -1 && temp_i < this.pole.length) {
                    if (temp_j > -1 && temp_j < this.pole.length) {
                        if (this.obsahuje_minu(temp_i, temp_j)){
                            suma += 1;
                        }
                    }
                }
            }
        }
        return suma;
    }
    
    
    
    void vypln_pole_sousedi() {
         for (int i = 0; i < this.pole.length; i++) {
            for (int j = 0; j < this.pole[0].length; j++) {
                if (this.obsahuje_minu(i, j))
                    this.pole[i][j] = -1;
                else
                    this.pole[i][j] = this.pocet_sousednich_min(i, j);
            }
        }
    }
    
    void prohledej(int x, int y) {
          this.pole_view[x][y] = -2;
        
          for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                if (i == 0 && j == 0)
                    continue;
                int temp_i = i + x;
                int temp_j = j + y;
                
                if (temp_i > -1 && temp_i < this.pole.length) {
                    if (temp_j > -1 && temp_j < this.pole.length) {
                        
                        if (this.pole[temp_i][temp_j] == 0
                                && this.pole_view[temp_i][temp_j] != -2 ) {
                            this.pole_view[temp_i][temp_j] = -2;
                            prohledej(temp_i, temp_j);
                        } else if (this.pole[temp_i][temp_j] != 0 && this.pole[temp_i][temp_j] != -1
                                && this.pole_view[temp_i][temp_j] != -2 ) {
                            this.pole_view[temp_i][temp_j] = -3;
                        }
                        
                    }
                }
                
            }
        }
    }
    
    void vypis_pole() {
        for (int i = 0; i < this.pole.length; i++) {
            for (int j = 0; j < this.pole[0].length; j++) {
                if (this.pole[i][j] == -1)
                    System.out.print("X");
                else if (this.pole[i][j] == -2)
                    System.out.print("A");
                else
                    System.out.print(this.pole[i][j]);
                System.out.print(" | ");
            }
            System.out.println("");
        }
    }
    
    void vypis_pole_view() {
        for (int i = 0; i < this.pole_view.length; i++) {
            for (int j = 0; j < this.pole_view[0].length; j++) {
                if (this.pole_view[i][j] == -1)
                    System.out.print("X");
                else if (this.pole_view[i][j] == -2)
                    System.out.print("A");
                else if (this.pole_view[i][j] == -3)
                    System.out.print("B");
                else
                    System.out.print(this.pole_view[i][j]);
                System.out.print(" | ");
            }
            System.out.println("");
        }
    }
    
    boolean check_vyhra() {
        for (int i = 0; i < this.pole_view.length; i++) {
            for (int j = 0; j < this.pole_view[0].length; j++) {
                
                if (this.pole[i][j] != -1) {
                    if (this.pole_view[i][j] != -2 && this.pole_view[i][j] != -3){
                        return false;
                    }
                }
                
            }
        }
        return true;
    }
    
    void klik(int i, int j) {
        if (this.obsahuje_minu(i, j)) {
            System.out.println("Prohrál jsi!");
        }
        else if (this.pole[i][j] == 0) {
            this.prohledej(i, j);
        }
        else if (this.pole[i][j] != -1){
            this.pole_view[i][j] = -2;
        }
        
        if (this.check_vyhra()) {
            System.out.println("Vyhrál jsi!");
        }
        
        
    }
    
}
