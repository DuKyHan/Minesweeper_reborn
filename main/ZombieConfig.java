public class ZombieConfig {
    private final Zombie.Type type;

    public ZombieConfig(Zombie.Type type) {
        this.type = type;
    }

    public Zombie.Type getType() {
        return type;
    }
}
