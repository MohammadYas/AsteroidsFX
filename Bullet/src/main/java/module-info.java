import dk.sdu.mmmi.mmy.common.bullet.BulletSPI;
import dk.sdu.mmmi.mmy.common.services.IEntityProcessingService;

module Bullet {
    requires Common;
    requires CommonBullet;

    provides BulletSPI with dk.sdu.mmmi.mmy.bulletsystem.BulletControlSystem;
    provides IEntityProcessingService with dk.sdu.mmmi.mmy.bulletsystem.BulletControlSystem;

    exports dk.sdu.mmmi.mmy.bulletsystem;
}
