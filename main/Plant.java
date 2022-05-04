import java.util.Timer;

public class Plant {
    public int x, y;
    private int hp = 45;
    private int damage = 5;
    private long lastFireAt = System.currentTimeMillis();

    public Plant (int x, int y)
    {
        this.x = x;
        this.y = y;

    }
    public static void setPosition(int x, int y)
    {
        x = x;
        y = y;
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
    public int Bitten(double damage){
        return hp -= damage;
    }
    public boolean alive(){
        return  hp>0;
    }
}
