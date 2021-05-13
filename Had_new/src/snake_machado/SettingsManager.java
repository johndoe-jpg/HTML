package snake_machado;

/**
 *
 * @author THUND
 */
public final class SettingsManager {
    
    private static int speed = 1000/20;
    private static int xSize = 50;
    private static int ySize = 50;
    
    private SettingsManager() {}
    
    

    public static void setSpeed(int speed) {
        SettingsManager.speed = speed;
    }

    public static void setxSize(int xSize) {
        SettingsManager.xSize = xSize;
    }

    public static void setySize(int ySize) {
        SettingsManager.ySize = ySize;
    }
    
    
    
    public static int getSpeed() {
        return speed;
    }

    public static int getxSize() {
        return xSize;
    }

    public static int getySize() {
        return ySize;
    }
}
