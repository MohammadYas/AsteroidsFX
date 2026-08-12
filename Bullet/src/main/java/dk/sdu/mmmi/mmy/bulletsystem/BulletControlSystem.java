package dk.sdu.mmmi.mmy.bulletsystem;

import dk.sdu.mmmi.mmy.common.bullet.Bullet;
import dk.sdu.mmmi.mmy.common.bullet.BulletSPI;
import dk.sdu.mmmi.mmy.common.data.Entity;
import dk.sdu.mmmi.mmy.common.data.GameData;
import dk.sdu.mmmi.mmy.common.data.World;
import dk.sdu.mmmi.mmy.common.services.IEntityProcessingService;

import java.util.List;

public class BulletControlSystem implements BulletSPI, IEntityProcessingService {

    private static final double BULLET_SPEED = 6;

    @Override
    public Entity createBullet(Entity shooter, GameData gameData) {
        Bullet bullet = new Bullet();
        bullet.setPolygonCoordinates(1, -1, 1, 1, -1, 1, -1, -1);
        bullet.setRadius(2);
        bullet.setOwnerID(shooter.getID());
        bullet.setRotation(shooter.getRotation());

        double radians = Math.toRadians(shooter.getRotation());
        double offset = shooter.getRadius() + 4;
        bullet.setX(shooter.getX() + Math.cos(radians) * offset);
        bullet.setY(shooter.getY() + Math.sin(radians) * offset);

        return bullet;
    }

    @Override
    public void process(GameData gameData, World world) {
        for (Bullet bullet : List.copyOf(world.getEntities(Bullet.class))) {
            double radians = Math.toRadians(bullet.getRotation());
            bullet.setX(bullet.getX() + Math.cos(radians) * BULLET_SPEED);
            bullet.setY(bullet.getY() + Math.sin(radians) * BULLET_SPEED);
            bullet.setLifeTime(bullet.getLifeTime() - 1);

            boolean offScreen = bullet.getX() < 0 || bullet.getX() > gameData.getDisplayWidth()
                    || bullet.getY() < 0 || bullet.getY() > gameData.getDisplayHeight();

            if (bullet.getLifeTime() <= 0 || offScreen) {
                world.removeEntity(bullet);
            }
        }
    }
}
