import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.Timer;
import java.util.TimerTask;

public class Zombie {
    private int hp = 300;
    private int damage = 20;
    private float spd = 0.3f;
    private int x = 1020,y;
    private static int[] arrY = {35,133,231,329,427};
    private static Timer timer;
    private long lastFireAt = System.currentTimeMillis();
    private long lastBiteAt = 0;
    ActionBehavior actionBehavior;


    public Zombie()
    {
        y = arrY[(int)(Math.random() * 5)];
    }
    public static void start(){
        timer = new Timer("zombie", false);
        timer.scheduleAtFixedRate(new TimerTask() {
        @Override
        public void run() {
            Loading.zombies.add(new NormalZombee());
        }
        }, 0, 10000);
    }

    public void move()
    {
        x -= spd;
    }

    public int getHp() {
        return hp;
    }

    public int getDamage() {
        return damage;
    }

    public float getSpd() {
        return spd;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public static int[] getArrY() {
        return arrY;
    }

    public static Timer getTimer() {
        return timer;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public boolean gameOver()
    {
        if(x>210)
        {
            return false;
        }
        else {
            return true;
        }
    }
    public void setLastFireAt(long lastFireAt) {
        this.lastFireAt = lastFireAt;
    }

    public long getLastFireAt() {
        return lastFireAt;
    }

    public long getLastBiteAt() {
        return lastBiteAt;
    }

    public void setLastBiteAt(long lastBiteAt) {
        this.lastBiteAt = lastBiteAt;
    }

    public enum Type {
        NORMAL,
        FLAG,
        CONE,
        FOOTBALL,
    }
}
