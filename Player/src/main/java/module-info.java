import dk.sdu.mmmi.mmy.common.services.IEntityProcessingService;
import dk.sdu.mmmi.mmy.common.services.IGamePluginService;

module Player {
    requires Common;
    requires CommonBullet;

    uses dk.sdu.mmmi.mmy.common.bullet.BulletSPI;

    provides IGamePluginService with dk.sdu.mmmi.mmy.playersystem.PlayerPlugin;
    provides IEntityProcessingService with dk.sdu.mmmi.mmy.playersystem.PlayerControlSystem;

    exports dk.sdu.mmmi.mmy.playersystem;
}
