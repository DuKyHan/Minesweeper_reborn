public class NewspaperZombie extends Zombee{
    public NewspaperZombie() {
        hp = 500;
        damage = 20;
        actionBehavior = new Angry();
    }

    @Override
    public void performAction() {
        if(hp  < 200);
        actionBehavior.Action(this);
    }
}
