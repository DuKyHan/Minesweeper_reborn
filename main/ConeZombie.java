public class ConeZombie extends Zombee{
    public ConeZombie() {
        hp = 500;
        damage = 10;
        actionBehavior = new Nobehavior();
        y = arrY[(int)(Math.random() * 5)];
    }

    @Override
    void performAction() {

    }
}
