package dk.sdu.mmmi.mmy.common.asteroids;

import dk.sdu.mmmi.mmy.common.data.Entity;
import dk.sdu.mmmi.mmy.common.data.World;

/**
 * Splits a destroyed asteroid into smaller ones.
 */
public interface IAsteroidSplitter {

    /**
     * Replaces the asteroid with two smaller asteroids, or removes it if it is
     * already at minimum size.
     *
     * Pre-condition: asteroid exists in world.
     * Post-condition: the original asteroid is removed from world.
     */
    void createSplitAsteroid(Entity asteroid, World world);
}
