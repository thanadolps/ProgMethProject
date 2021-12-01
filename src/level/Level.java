package level;

import level.spawner.Spawner;
import utils.TileGrid;

// static information about the level
public abstract class Level {
    public abstract TileGrid getTileGrid();
    public abstract Spawner nextSpawner();
}
