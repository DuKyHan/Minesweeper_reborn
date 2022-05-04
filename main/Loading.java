import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Ellipse2D;
import java.io.IOException;
import java.util.*;
import java.util.List;

public class Loading extends JPanel implements ActionListener {
    private Shape[][] field = new Shape[5][9];
    public boolean play = false;
    private Timer timer;
    public static Vector<Plant> plants = new Vector<>();
    public static java.util.List<Pea> peas = new Vector<>();
    public static java.util.List<Zombee> zombies = new Vector<>();
    public static java.util.List<Sunflower> sunflowers = new Vector<>();
    public static Vector<Sun> suns = new Vector<>();
    private Toolkit t = Toolkit.getDefaultToolkit();
    private Point mouse = new Point();
    private Numbers numbers;
    Picture picture;
    Image image = t.getImage(getClass().getResource("img/Menu.jpg"));
    private long startTime = -1;
    private long lastSpawnSunTime = -1;
    private final long sunPeriod = 10000;
    private int[] fw = {0, 90, 165, 250, 330, 410, 492, 570, 651, 749}; //field width
    private int[] fh = {0, 118, 215, 323, 405, 516}; //field height
    private final int FPS = 25;
    private int numZombie =0;
    private long spawnZombieAt = System.currentTimeMillis();
    public static final Random RANDOM = new Random();

    // Waves
    private java.util.List<Wave> waves = new ArrayList<>();
    private List<Zombee.Type> zombiesToSpawn;
    private int currentZombieIndex;
    private int currentWaveIndex = -1;
    private final ZombeeFactory zombeeFactory = new ZombeeFactory();

    public Loading() throws IOException, FontFormatException {
        numbers = Numbers.getInstance();
        timer = new Timer(FPS, this);
        if (!play) {
            timer.start();
        }
        addMouseListener(new MListener());
        addMouseMotionListener(new MouseMotionAdapter() { //listen to mouse motion
            public void mouseMoved(MouseEvent e) {
                mouse.setX(e.getX());
                mouse.setY(e.getY());
            }
        });
        picture = new Picture();

        Set<Zombee.Type> zombies = new HashSet<>();
        zombies.add(Zombee.Type.CONE);
        zombies.add(Zombee.Type.NORMAL);
        Wave wave = new Wave(zombies, 5);
        waves.add(wave);
        waves.add(wave);
    }

    private Rectangle rec = new Rectangle(0, 0, 1024, 625);

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (!play) {
            // Bg
            g.drawImage(image, 0, 0, 1024, 625, this);
            plants.clear();


        } else {
            Graphics2D g2 = (Graphics2D) g;
            // Gameplay
            g.drawImage(picture.images.get(1), 0, 0, 1024, 625, this);
            g.drawImage(picture.images.get(7), 15, 22, 150, 580, this);
            numbers.draw(g2);
            //plant
            Iterator<Plant> itp = plants.iterator();
            while (itp.hasNext()) {
                Plant plant = itp.next();
                g.drawImage(picture.images.get(3), plant.getX(), plant.getY(), 64, 66, this);

            }
            Iterator<Zombee> itz = zombies.iterator();
            for (Zombee zombie : zombies.stream().toList()) {

                // Zom
                if(zombie instanceof NormalZombee)
                {g.drawImage(picture.images.get(2), zombie.getX(), zombie.getY(), 73, 119, this);}
                if(zombie instanceof ConeZombie)
                {g.drawImage(picture.images.get(8), zombie.getX(), zombie.getY(), 73, 119, this);
                }

                if (zombie.gameOver()) {
                    play = false;
                    zombies.clear();
                    plants.clear();
                    suns.clear();
                    peas.clear();
                    numbers.resetCredits();
                    sunflowers.clear();
                }

            }


            //pea
            Iterator<Pea> itpe = peas.iterator();
            for (Pea pea : peas.stream().toList()) {
                g.drawImage(picture.images.get(4), pea.getX(), pea.getY(), 30, 30, this);
                pea.move();
                if (pea.getX() > 1300) {
                    peas.remove(pea);
                }
            }
            Iterator<Sunflower> itsunf = sunflowers.iterator();
            while (itsunf.hasNext()) {
                Sunflower sunflower = itsunf.next();
                g.drawImage(picture.images.get(6), sunflower.getX(), sunflower.getY(), 64, 66, this);
            }
            Iterator<Sun> itsun = suns.iterator();
            while (itsun.hasNext()) {
                Sun sun = itsun.next();
                g.drawImage(picture.images.get(5), sun.getSunX(), sun.getSunY(), 80, 80, this);
                sun.setE(new Ellipse2D.Float(sun.getSunX(), sun.getSunY(), 80, 80));
                sun.move();
                if (!sun.isLive()) {
                    itsun.remove();
                }
            }
            if (numbers.getChoice() == 1) {
                g2.setComposite(AlphaComposite.SrcOver.derive(0.7f));
                g2.drawImage(picture.images.get(6), mouse.getX() - 62 / 2, mouse.getY() - 71 / 2, 62, 71, this);
                g2.setComposite(AlphaComposite.SrcOver.derive(1f));
            } else if (numbers.getChoice() == 2) {
                g2.setComposite(AlphaComposite.SrcOver.derive(0.7f));
                g2.drawImage(picture.images.get(3), mouse.getX() - 62 / 2 + 1, mouse.getY() - 66 / 2, 62 + 2, 66, this);
                g2.setComposite(AlphaComposite.SrcOver.derive(1f));
            }
        }
        g.dispose();
    }

    private void spawnSun() {
        long current = System.currentTimeMillis();
        if (lastSpawnSunTime < 0) {
            return;
        }
        if (current - lastSpawnSunTime < sunPeriod) {
            return;
        }
        suns.add(new Sun((int) Math.random() * (974 - 230 + 1) + 230, 0));
        lastSpawnSunTime = current;
    }

    private void spawnWaves() {
        if (!play) {
            return;
        }

        if (zombiesToSpawn == null && zombies.isEmpty()) {
            currentWaveIndex++;
        }

        if (currentWaveIndex >= waves.size()) {
            // end
            return;
        }

        if (zombiesToSpawn == null && zombies.isEmpty()) {
            zombiesToSpawn = waves.get(currentWaveIndex).getRandomZombies();
            currentZombieIndex = 0;
        }

        if (zombiesToSpawn != null) {
            spawnZom();
        }
    }

    private void spawnZom() {
        int i = 0;
        if( i < 3)
        {
            if (System.currentTimeMillis() - spawnZombieAt < 20000) {
                return;
            }
            if (currentZombieIndex >= zombiesToSpawn.size()) {
                zombiesToSpawn = null;
                return;
            }

            spawnZombieAt = System.currentTimeMillis();
            Zombee.Type type = zombiesToSpawn.get(currentZombieIndex);
            zombies.add(ZombeeFactory.createZombee(type));

            currentZombieIndex++;

        }
        else if(i < 10){
            if (System.currentTimeMillis() - spawnZombieAt < 3000) {
                return;
            }
            if (currentZombieIndex >= zombiesToSpawn.size()) {
                zombiesToSpawn = null;
                return;
            }

            spawnZombieAt = System.currentTimeMillis();
            Zombee.Type type = zombiesToSpawn.get(currentZombieIndex);
            zombies.add(ZombeeFactory.createZombee(type));

            currentZombieIndex++;
        }





    }

    @Override
    public void actionPerformed(ActionEvent e) {
        spawnSun();
        repaint();
        spawnPeas();
        spawnSuns();
        spawnWaves();
        executeZombieWorks();
        clearDeadObjects();
    }


    private void spawnPeas() {
        for (Plant plant : plants) {
            for (Zombee zombie : zombies) {
                if (Math.abs(zombie.getY() - plant.getY()) == 55 && plant.getX() < zombie.getX()){
                    if (System.currentTimeMillis() - plant.getLastFireAt() < 1000) {
                        break;
                    }
                    peas.add(new Pea(plant.x, plant.y));
                    plant.setLastFireAt(System.currentTimeMillis());
                }
            }

        }
    }

    private void spawnSuns() {
        for (Sunflower sunflower : sunflowers) {
            if (System.currentTimeMillis() - sunflower.getLastFireAt() < 5000) {
                continue;
            }
            suns.add(new Sun(sunflower.x, sunflower.y));
            sunflower.setLastFireAt(System.currentTimeMillis());
        }
    }

    private void executeZombieWorks() {
        for (Zombee zombie : zombies) {
            boolean shouldMove = true;

            for (Plant plant : plants) {
                if(Math.abs(zombie.getX()- plant.getX()) < 30 && Math.abs(plant.getY() - zombie.getY()) == 55 )
                {
                    shouldMove = false;
                    if (System.currentTimeMillis() - zombie.getLastBiteAt() < 1000) {
                        continue;
                    }
                    plant.Bitten(zombie.getDamage());
                    zombie.setLastBiteAt(System.currentTimeMillis());
                }
            }
            for (Pea pea : peas.stream().toList()) {
                if (pea.getX() > zombie.getX() && pea.getX() < zombie.getX() + 73 && pea.getY() > zombie.getY() && pea.getY() < zombie.getY() + 119) {
                    boolean t = peas.remove(pea);
                    zombie.setHp(zombie.getHp() - 25);
                }
            }

            if (shouldMove) {
                zombie.move();
            }
        }
    }

    private void clearDeadObjects() {
        suns.removeIf(sun -> !sun.isLive());
        plants.removeIf(plant -> !plant.alive());
        zombies.removeIf(zombee -> zombee.getHp() <= 0);
    }

    private class MListener extends MouseAdapter {
        @Override
        public void mousePressed(MouseEvent e) {
            super.mousePressed(e);
            if (rec.contains(e.getPoint())) {
                play = true;
                lastSpawnSunTime = System.currentTimeMillis();
                //Zombie.start();
            }
            if (play) {
                for (Sun a : suns) {
                    if (a.getE().contains(e.getPoint())) {
                        numbers.addSunCredits();
                        a.setMarkForRemoval(true);
                    }
                }
                if (picture.rectangles.get(0).contains(e.getPoint())) {
                    if (numbers.getCredits() >= 50) {
                        numbers.setChoice((numbers.getChoice() == 1) ? 0 : 1);
                    }
                }
                if (picture.rectangles.get(1).contains(e.getPoint())) {
                    if (numbers.getCredits() >= 100) {
                        numbers.setChoice((numbers.getChoice() == 2) ? 0 : 2);
                    }
                }
                if (numbers.getChoice() != 0) {
                    for (int i = 0; i < 5; i++) {
                        for (int j = 0; j < 9; j++) {
                            field[i][j] = new Rectangle(245 + fw[j], 50 + fh[i], fw[j + 1] - fw[j], fh[i + 1] - fh[i]);
                            Plant.setPosition(296 + j * 81, 117 + i * 98);
                        }
                    }
                    for (int i = 0; i < 5; i++) {
                        for (int j = 0; j < 9; j++) {
                            if (field[i][j].contains(e.getPoint())) {
                                if (Numbers.getInstance().put(i, j, numbers.getChoice())) {
                                    numbers.setChoice(0);
                                }
                                numbers.setChoice(0);
                            }
                        }
                    }

                }
            }
        }
    }
}

