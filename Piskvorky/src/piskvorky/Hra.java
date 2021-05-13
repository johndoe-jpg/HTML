
package piskvorky;

import static javax.swing.JOptionPane.showMessageDialog;



public class Hra {
    public int x = 5;//vyska
    public int y = 6;//sirka
    public int vnitrni_pole[][];//pole
    public String player1 = "X";
    public String player2 = "O";
    public int curr_player = 1;
    public String curr_symbol = player1;
    private static Hra instance=null;
    private Hra (){
        vnitrni_pole = new int[x][y];
//        vnitrni_pole[0][4]= 1;
//        vnitrni_pole[1][3]= 1;
//        vnitrni_pole[2][2]= 1;
//        vnitrni_pole[3][1]= 1;
//        vnitrni_pole[4][0]= 1;
    }//konstruktor
    
    public static Hra getInstance() 
    { 
        if (instance == null) 
            instance = new Hra(); 
  
        return instance; 
    } 
    
    public void JePet(){
        int prevPole = 0;//mezi promena
        int score = 0;//score
        //promene
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {              
                if (vnitrni_pole[i][j]!=0) {
                    if (prevPole == vnitrni_pole[i][j]) {
                        score++;
                        System.out.println("score "+score);
                        if (score==4) {
                            System.out.println("win");
                            showMessageDialog(null, "Vyhrál: "+curr_symbol);
//                            vk.setVisible(true);
//                            vh.setVisible(true);
                            
                        }
                    }else{
                        score=0;
                    }
                }//if
                prevPole = vnitrni_pole[i][j];
            }//j
            score=0;
            prevPole=0;//po dokoneceni radku se resetuje counter
        }//i - Kontrola radku     
        
        for (int i = 0; i < y; i++) {
            for (int j = 0; j < x; j++) {              
                if (vnitrni_pole[j][i]!=0) {
                    if (prevPole == vnitrni_pole[j][i]) {
                        score++;
                        //System.out.println("score "+score);
                        if (score==4) {
                            System.out.println("win");
                            showMessageDialog(null, "Vyhrál: "+curr_symbol);
                        }
                    }else{
                        score=0;
                    }
                }//if
                prevPole = vnitrni_pole[j][i];
            }//j
            score=0;
            prevPole=0;//po dokoneceni sloupce se resetuje counter
        }//i - Kontrola sloupcu 
        
        //kontrola diagonalne t leva do prava, pokud se jedna o ctverec
        if (x==y) {
            for (int k = 0; k <= x-1; k++) {
            int i=k;
            int j=0;
            while(i>=0){
                //System.out.print(vnitrni_pole[i][j]);
                if (vnitrni_pole[i][j]!=0) {
                    if (prevPole == vnitrni_pole[i][j]) {
                        score++;
                        System.out.println("score "+score);
                        if (score==4) {
                            System.out.println("win");
                            showMessageDialog(null, "Vyhrál: "+curr_symbol);
                        }
                    }else{
                        score=0;
                    }
                }//if
                prevPole = vnitrni_pole[i][j];
                i=i-1;
                j=j+1;          
            }//while
            //System.out.println("");
            prevPole = 0;
        }//for prvni polovina
        
        for (int k = 1; k <= y-1; k++) {
            int i=x-1;
            int j=k;
            while(j<=y-1){
                //System.out.print(vnitrni_pole[i][j]);
                if (vnitrni_pole[i][j]!=0) {
                    if (prevPole == vnitrni_pole[i][j]) {
                        score++;
                        System.out.println("score "+score);
                        if (score==4) {
                            System.out.println("win");
                            showMessageDialog(null, "Vyhrál: "+curr_symbol);
                        }
                    }else{
                        score=0;
                    }
                }//if
                prevPole = vnitrni_pole[i][j];
                i=i-1;
                j=j+1;
            }//while
            //System.out.println("");
            prevPole = 0;
        }//for druha polovina
        }//kontrola diagonalne t leva do prava, pokud se jedna o ctverec
        
        if (x!=y) {
            for(int k=0; k<=x+y-2; k++) {
        for(int j=0; j<=k; j++) {
            int i=k-j;
            if(i<x && j<y) {
                //System.out.print(vnitrni_pole[i][j]+" ");
                //System.out.print(vnitrni_pole[i][j]);
                if (vnitrni_pole[i][j]!=0) {
                    if (prevPole == vnitrni_pole[i][j]) {
                        score++;
                        System.out.println("score "+score);
                        if (score==4) {
                            System.out.println("win");
                            showMessageDialog(null, "Vyhrál: "+curr_symbol);
                        }
                    }else{
                        score=0;
                    }
                }//if
                prevPole = vnitrni_pole[i][j];
            }//if
        }//for j
        System.out.println();
        prevPole = 0;
    }//for k
        }//kontrola diagonalne t leva do prava, pokud se jedna o ctverec
        
    }//je pet za sebou?
    
    public void Tisk(){
        for (int i = 0; i < x; i++) {
            System.out.print("[");
            for (int j = 0; j < y; j++) {
                System.out.print(vnitrni_pole[i][j]);
                System.out.print(", ");
            }
            System.out.println("]");
        }
    }
    
    public void NastavVelikost(int x_in, int y_in){       
        x=x_in;
        y=y_in;
        vnitrni_pole = new int[x][y];
        Tisk();
    }//nastav velikost
}
