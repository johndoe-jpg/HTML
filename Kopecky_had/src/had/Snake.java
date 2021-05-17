package had;

import java.util.ArrayList;

public class Snake {

    private int delka;
    private int smer;
    private ArrayList<Position> telo = new ArrayList<>();
    private Position ocasek;

    public Snake(Position hlava, int smer) {
        telo.add(hlava);
        this.smer = smer;
    }

    public void snezJablicko() {
        delka++;
        telo.add(ocasek);
    }

    public void setSmer(int smer) {
        this.smer = smer;
    }

    public void tick() {
        ocasek = new Position(telo.get(telo.size() - 1).x, telo.get(telo.size() - 1).y);

        for (int i = telo.size() - 1; i > 0; i--) {
            telo.get(i).copy(telo.get(i - 1));
        }

        if (smer == 0) {
            getPoziceHlavy().y -= 1;
        } else if (smer == 1) {
            getPoziceHlavy().x += 1;
        } else if (smer == 2) {
            getPoziceHlavy().y += 1;
        } else {
            getPoziceHlavy().x -= 1;
        }
    }

    public int getDelka() {
        return delka;
    }

    public int getSmer() {
        return smer;
    }

    public ArrayList<Position> getTelo() {
        return telo;
    }

    public Position getPoziceHlavy() {
        return telo.get(0);
    }

}
