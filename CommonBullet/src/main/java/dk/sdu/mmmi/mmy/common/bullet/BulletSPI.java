package dk.sdu.mmmi.mmy.common.bullet;

import dk.sdu.mmmi.mmy.common.data.Entity;
import dk.sdu.mmmi.mmy.common.data.GameData;

public interface BulletSPI {

    Entity createBullet(Entity shooter, GameData gameData);
}
