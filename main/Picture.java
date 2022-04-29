import java.awt.*;
import java.util.LinkedList;

public class Picture {
    public static LinkedList<Image> images = new LinkedList<>();
    public static LinkedList<Rectangle> rectangles = new LinkedList<>();
    private Toolkit t = Toolkit.getDefaultToolkit();
    private Shape[][] field = new Shape[5][9];

    public Picture()
    {
        images.add(t.getImage(getClass().getResource("img/Menu.jpg")));//0
        images.add(t.getImage(getClass().getResource("img/Play.jpg")));//1
        images.add(t.getImage(getClass().getResource("img/Zombie.gif")));//2
        images.add(t.getImage(getClass().getResource("img/Peashooter.gif")));//3
        images.add(t.getImage(getClass().getResource("img/Pea.png")));//4
        images.add(t.getImage(getClass().getResource("img/Sun.png")));//5
        images.add(t.getImage(getClass().getResource("img/Sunflower.gif")));//6
        images.add(t.getImage(getClass().getResource("img/playboard.png")));//7
        rectangles.add(new Rectangle(23, 156, 62+73, 66+21));//0
        rectangles.add(new Rectangle(23, 249, 62+73, 66+12));//1

//        rec[3] = new Rectangle(23, 156, pwidth+73, pheight+21); //sunflower
//        rec[4] = new Rectangle(23, 249, pwidth+73, pheight+12); //peashooter
//        rec[5] = new Rectangle(23, 333, pwidth+73, pheight+14); //repeater
//        rec[6] = new Rectangle(23, 419, pwidth+73, pheight+17); //wallnut
//        rec[7] = new Rectangle(23, 508, pwidth+73, pheight+19); //cherrybomb

    }

}
