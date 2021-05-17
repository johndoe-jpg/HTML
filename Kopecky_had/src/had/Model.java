package had;

import java.awt.event.KeyEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.Timer;

public class Model {

    private int x;
    private int y;
    private Snake snake;
    private Position jablicko;
    private Timer t;
    boolean konec = false;

    private Model() {

    }

    private void tick() {
        snake.tick();

        if (snake.getPoziceHlavy().x < 0) {
            snake.getPoziceHlavy().x = x - 1;
        } else if (snake.getPoziceHlavy().y < 0) {
            snake.getPoziceHlavy().y = y - 1;
        } else if (snake.getPoziceHlavy().x >= x) {
            snake.getPoziceHlavy().x = 0;
        } else if (snake.getPoziceHlavy().y >= y) {
            snake.getPoziceHlavy().y = 0;
        }

        if (snake.getPoziceHlavy().equals(jablicko)) {
            snake.snezJablicko();
            respawniJablicko();
        }

        ArrayList<Position> telo = new ArrayList<>();
        telo.addAll(snake.getTelo());
        telo.remove(0);
        for (Position p : telo) {
            if (p.equals(snake.getPoziceHlavy())) {
                stop();
                propertyChangeSupport.firePropertyChange("konec", false, true);
            }
        }

        propertyChangeSupport.firePropertyChange("t", null, t);
    }

    public Timer getT() {
        return t;
    }

    public void start(int x, int y) {
        this.x = x;
        this.y = y;
        snake = new Snake(new Position(x / 2, y / 2), 3);
        respawniJablicko();
        t = new Timer(200, (e) -> {
            tick();
        });
        t.start();
    }

    public void restart(int x, int y) {
        t.stop();
        t = null;
        snake = null;
        start(x, y);
    }

    public void pause() {
        t.stop();
    }

    public void resume() {
        t.start();
    }

    public void stop() {
        konec = true;
        t.stop();
    }

    private void respawniJablicko() {
        Random r = new Random();
        boolean obsahuje = true;
        Position p = new Position(0, 0);
        while (obsahuje) {
            p = new Position(r.nextInt(x), r.nextInt(y));
            for (Position position : snake.getTelo()) {
                if (p.equals(position)) {
                    obsahuje = true;
                    break;
                } else {
                    obsahuje = false;
                }
            }
        }
        jablicko = p;
    }

    public Position getJablicko() {
        return jablicko;
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            snake.setSmer(0);
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            snake.setSmer(1);
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            snake.setSmer(2);
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            snake.setSmer(3);
        }
    }

    public Snake getSnake() {
        return snake;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    private transient final PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.removePropertyChangeListener(listener);
    }

    public static Model getInstance() {
        return ModelHolder.INSTANCE;
    }

    private static class ModelHolder {

        private static final Model INSTANCE = new Model();
    }

}
