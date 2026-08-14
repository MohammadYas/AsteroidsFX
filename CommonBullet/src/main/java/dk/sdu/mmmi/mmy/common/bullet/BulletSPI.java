package dk.sdu.mmmi.mmy.common.bullet;

import dk.sdu.mmmi.mmy.common.data.Entity;
import dk.sdu.mmmi.mmy.common.data.GameData;

/**
 * Creates bullets on behalf of a shooting entity.
 */
public interface BulletSPI {

    /**
     * Creates a bullet travelling in the shooter's current direction.
     *
     * Pre-condition: shooter is a live entity and gameData is not null.
     * Post-condition: a new bullet is returned, placed in front of the shooter.
     */
    Entity createBullet(Entity shooter, GameData gameData);
}
