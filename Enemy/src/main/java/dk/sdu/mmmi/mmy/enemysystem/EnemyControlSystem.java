package dk.sdu.mmmi.mmy.enemysystem;

import dk.sdu.mmmi.mmy.common.bullet.BulletSPI;
import dk.sdu.mmmi.mmy.common.data.GameData;
import dk.sdu.mmmi.mmy.common.data.World;
import dk.sdu.mmmi.mmy.common.services.IEntityProcessingService;

import java.util.Random;
import java.util.ServiceLoader;

public class EnemyControlSystem implements IEntityProcessingService {

    private static final double TURN_CHANCE = 0.02;
    private static final double SHOOT_CHANCE = 0.01;

    private final Random random = new Random();

    @Override
    public void process(GameData gameData, World world) {
        for (Enemy enemy : world.getEntities(Enemy.class)) {
            if (random.nextDouble() < TURN_CHANCE) {
                enemy.setRotation(enemy.getRotation() + (random.nextDouble() - 0.5) * 90);
            }

            double radians = Math.toRadians(enemy.getRotation());
            enemy.setX(enemy.getX() + Math.cos(radians) * enemy.getSpeed());
            enemy.setY(enemy.getY() + Math.sin(radians) * enemy.getSpeed());

            if (enemy.getX() < 0) {
                enemy.setX(gameData.getDisplayWidth());
            }
            if (enemy.getX() > gameData.getDisplayWidth()) {
                enemy.setX(0);
            }
            if (enemy.getY() < 0) {
                enemy.setY(gameData.getDisplayHeight());
            }
            if (enemy.getY() > gameData.getDisplayHeight()) {
                enemy.setY(0);
            }

            if (random.nextDouble() < SHOOT_CHANCE) {
                ServiceLoader.load(BulletSPI.class).findFirst()
                        .ifPresent(spi -> world.addEntity(spi.createBullet(enemy, gameData)));
            }
        }
    }
}
