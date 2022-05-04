public class Angry implements ActionBehavior{
    @Override
    public void Action(Zombee zombee) {
        zombee.speed = 0.5f;
    }
}
