import java.util.Timer;
import java.util.TimerTask;

public class Sunflower {
    public int x, y;
    private int hp = 50;
    private static int[][] occ = new int[5][10];
    private long lastFireAt = System.currentTimeMillis();

    public Sunflower (int x, int y)
    {
        this.x = x;
        this.y = y;

    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getHp() {
        return hp;
    }


    public void setLastFireAt(long lastFireAt) {
        this.lastFireAt = lastFireAt;
    }

    public long getLastFireAt() {
        return lastFireAt;
    }
}
