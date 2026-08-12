import dk.sdu.mmmi.mmy.common.services.IEntityProcessingService;
import dk.sdu.mmmi.mmy.common.services.IGamePluginService;

module Enemy {
    requires Common;
    requires CommonBullet;

    uses dk.sdu.mmmi.mmy.common.bullet.BulletSPI;

    provides IGamePluginService with dk.sdu.mmmi.mmy.enemysystem.EnemyPlugin;
    provides IEntityProcessingService with dk.sdu.mmmi.mmy.enemysystem.EnemyControlSystem;

    exports dk.sdu.mmmi.mmy.enemysystem;
}
