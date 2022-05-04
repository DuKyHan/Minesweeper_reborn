import java.awt.desktop.SystemEventListener;
import java.awt.geom.Ellipse2D;

public class Sun {
    private  int sunX, sunY;
    private double lastPositionY;
    private boolean waiting = false;
    private long lastSpawnTime = 0;
    private long spawntime = System.currentTimeMillis();
    private Ellipse2D e_sun;
    private boolean markForRemoval = false;

    public Sun(int sunX, int sunY) {
        this.sunX = sunX;
        this.sunY = sunY;
        this.lastPositionY = Math.random() * 575;
    }
    public boolean isLive()
    {
        return !markForRemoval && System.currentTimeMillis() - spawntime <= 3000;
    }

    public void move()
    {
        if(sunY > lastPositionY)
        {
            return;
        }
        sunY += 1;
    }
    public Ellipse2D getE(){return e_sun;}
    public void setE(Ellipse2D e_sun){
        this.e_sun=e_sun;
    }
    public int getSunX() {
        return sunX;
    }

    public void setSunX(int sunX) {
        this.sunX = sunX;
    }

    public int getSunY() {
        return sunY;
    }

    public void setSunY(int sunY) {
        this.sunY = sunY;
    }

    public boolean isWaiting() {
        return waiting;
    }

    public void setWaiting(boolean waiting) {
        this.waiting = waiting;
    }

    public long getLastSpawnTime() {
        return lastSpawnTime;
    }

    public void setLastSpawnTime(long lastSpawnTime) {
        this.lastSpawnTime = lastSpawnTime;
    }

    public boolean isMarkForRemoval() {
        return markForRemoval;
    }

    public void setMarkForRemoval(boolean markForRemoval) {
        this.markForRemoval = markForRemoval;
    }
}
