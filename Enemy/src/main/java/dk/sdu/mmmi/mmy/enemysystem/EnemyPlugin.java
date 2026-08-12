package dk.sdu.mmmi.mmy.enemysystem;

import dk.sdu.mmmi.mmy.common.data.GameData;
import dk.sdu.mmmi.mmy.common.data.World;
import dk.sdu.mmmi.mmy.common.services.IGamePluginService;

import java.util.Random;

public class EnemyPlugin implements IGamePluginService {

    private static final int ENEMY_COUNT = 3;

    private final Random random = new Random();

    @Override
    public void start(GameData gameData, World world) {
        for (int i = 0; i < ENEMY_COUNT; i++) {
            Enemy enemy = new Enemy();
            enemy.setPolygonCoordinates(-6, -6, 8, 0, -6, 6);
            enemy.setX(random.nextInt(gameData.getDisplayWidth()));
            enemy.setY(random.nextInt(gameData.getDisplayHeight()));
            enemy.setRotation(random.nextInt(360));
            enemy.setRadius(8);
            enemy.setSpeed(2.2);
            enemy.setHitPoints(2);
            world.addEntity(enemy);
        }
    }

    @Override
    public void stop(GameData gameData, World world) {
        for (Enemy enemy : world.getEntities(Enemy.class)) {
            world.removeEntity(enemy);
        }
    }
}
