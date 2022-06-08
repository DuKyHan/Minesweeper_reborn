import java.awt.desktop.SystemEventListener;
import java.awt.geom.Ellipse2D;
//Factory design pattern
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

    public int getSunY() {
        return sunY;
    }

    public void setMarkForRemoval(boolean markForRemoval) {
        this.markForRemoval = markForRemoval;
    }
}
