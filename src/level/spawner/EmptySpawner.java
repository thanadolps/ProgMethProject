package level.spawner;

public class EmptySpawner extends Spawner {
    private double countdown;

    public EmptySpawner(double delay) {
        countdown = delay;
    }

    @Override
    public void tick(double dt) {
        countdown -= dt;
    }

    @Override
    public boolean isDone() {
        return countdown <= 0;
    }
}
