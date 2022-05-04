public class NormalZombee extends Zombee{
    public NormalZombee() {
        hp = 300;
        damage = 10;
        actionBehavior = new Nobehavior();
        y = arrY[(int)(Math.random() * 5)];
    }

    @Override
    public void performAction() {
        actionBehavior.Action(this);
    }
}
