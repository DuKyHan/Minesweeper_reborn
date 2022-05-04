import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Numbers {
    private int sunCredits =50,choice = 0;
    private boolean shovel = false;
    private static int[][] occ = new int[5][10];
    private Font font;

    public Numbers() {
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, getClass().getResource("img/Chalkboard.ttc").openStream()).deriveFont(Font.BOLD, 20f);
        } catch (FontFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        ge.registerFont(font);
        int[] fw = {0,90,165,250,330,410,492,570,651,749}; //field width
        int[] fh = {0,118,215,323,405,516}; //field height
    }

    private static Numbers numbers = null;

    public static Numbers getInstance() {
        if (numbers == null) {
            numbers = new Numbers();
        }
        return numbers;
    }

    public void draw(Graphics2D g) {
        g.setFont(font);
        g.setColor(Color.BLACK);
        FontMetrics metrics = g.getFontMetrics(font);
        g.drawString(Integer.toString(sunCredits), 91 - (metrics.stringWidth(Integer.toString(sunCredits)) / 2), 136);
    }
    public int getCredits () {
        return sunCredits;
    }
    public void addSunCredits () {
        sunCredits += 25;
    }
    public void resetCredits () {
        sunCredits = 50;
    }

    public int getChoice () {
        return choice;
    }
    public void setChoice ( int choice){
        this.choice = choice;
    }

    public boolean getShovel () {
        return shovel;
    }
    public void setShovel( boolean shovel){
        this.shovel = shovel;
    }
    public boolean put(int x, int y, int choice){
        if(occ[x][y]==0){ //empty spot


            switch (choice) {
                case 1:
                    sunCredits -= 50;
                    Loading.sunflowers.add(new Sunflower( 265 + y * 81, 90 + x * 98));//90 188 286  384  482    35,133,231,329,427
                    break; //sunflower
                case 2:
                    sunCredits -= 100;
                    Loading.plants.add(new Plant( 265 + y * 81, 90 + x * 98));
                    break; //peashooter
            }
            occ[x][y] = 1;
            return true;
        }else{
            return false;
        }
    }
}
