public class ZombeeFactory {
    public static Zombee createZombee(Zombee.Type type) {
        return switch (type) {
            case NORMAL -> new NormalZombee();
            case CONE -> new ConeZombie();
            default -> null;
        };
    }
}
