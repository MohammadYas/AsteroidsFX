package dk.sdu.mmmi.mmy.common.asteroids;

import dk.sdu.mmmi.mmy.common.data.Entity;
import dk.sdu.mmmi.mmy.common.data.World;

public interface IAsteroidSplitter {

    void createSplitAsteroid(Entity asteroid, World world);
}
