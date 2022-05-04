import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class Wave {
    private final Set<Zombee.Type> zombies;
    private final int maxZombies;

    public Wave(Set<Zombee.Type> zombies, int maxZombies) {
        this.zombies = zombies;
        this.maxZombies = maxZombies;
    }

    public Set<Zombee.Type> getZombies() {
        return zombies;
    }

    public int getMaxZombies() {
        return maxZombies;
    }

    public Zombee.Type getRandomZombie() {
        List<Zombee.Type> candidates = new ArrayList<>(zombies);
        int chosenIndex = Loading.RANDOM.nextInt(zombies.size());
        return candidates.get(chosenIndex);
    }

    public List<Zombee.Type> getRandomZombies() {
        List<Zombee.Type> zombies = new ArrayList<>();
        for (int i = 0; i < maxZombies; i++) {
            zombies.add(getRandomZombie());
        }
        return zombies;
    }
}
