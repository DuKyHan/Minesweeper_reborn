import java.util.Timer;
import java.util.TimerTask;

public abstract class Zombee {
    int hp;
    int damage;
    float speed = 0.3f;
    ActionBehavior actionBehavior;
    int x = 1020,y;
    static int[] arrY = {35,133,231,329,427};
    static Timer timer;
    long lastFireAt = System.currentTimeMillis();
    long lastBiteAt = 0;

    void move()
    {
        x -= speed;
    }

    int getHp() {
        return hp;
    }

    int getDamage() {
        return damage;
    }

    float getSpd() {
        return speed;
    }

    int getX() {
        return x;
    }

    int getY() {
        return y;
    }


    void setHp(int hp) {
        this.hp = hp;
    }

    boolean gameOver()
    {
        if(x>210)
        {
            return false;
        }
        else {
            return true;
        }
    }

    long getLastBiteAt() {
        return lastBiteAt;
    }

    void setLastBiteAt(long lastBiteAt) {
        this.lastBiteAt = lastBiteAt;
    }
    abstract void performAction();
    public enum Type {
        NORMAL,
        FLAG,
        CONE,
        FOOTBALL,
    }
}
