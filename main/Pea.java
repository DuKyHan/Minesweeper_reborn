import java.util.Objects;

public class Pea {
    private int damage;
    private int x,y;
    public Pea(int x,int y)
    {
        this.x = x;
        this.y = y;
    }
    public void move(){
        x += 6;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pea pea = (Pea) o;
        return damage == pea.damage && x == pea.x && y == pea.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(damage, x, y);
    }
}
