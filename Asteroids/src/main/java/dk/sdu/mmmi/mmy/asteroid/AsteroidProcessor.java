package dk.sdu.mmmi.mmy.asteroid;

import dk.sdu.mmmi.mmy.common.asteroids.Asteroid;
import dk.sdu.mmmi.mmy.common.data.GameData;
import dk.sdu.mmmi.mmy.common.data.World;
import dk.sdu.mmmi.mmy.common.services.IEntityProcessingService;

public class AsteroidProcessor implements IEntityProcessingService {

    private static final int MINIMUM_ASTEROIDS = 5;
    private static final int SPAWN_DELAY = 120;

    private int framesSinceSpawn = 0;

    @Override
    public void process(GameData gameData, World world) {
        framesSinceSpawn++;
        if (world.getEntities(Asteroid.class).size() < MINIMUM_ASTEROIDS && framesSinceSpawn >= SPAWN_DELAY) {
            framesSinceSpawn = 0;
            world.addEntity(AsteroidPlugin.createAsteroid(gameData));
        }

        for (Asteroid asteroid : world.getEntities(Asteroid.class)) {
            double radians = Math.toRadians(asteroid.getRotation());
            asteroid.setX(asteroid.getX() + Math.cos(radians) * asteroid.getSpeed());
            asteroid.setY(asteroid.getY() + Math.sin(radians) * asteroid.getSpeed());

            if (asteroid.getX() < 0) {
                asteroid.setX(gameData.getDisplayWidth());
            }
            if (asteroid.getX() > gameData.getDisplayWidth()) {
                asteroid.setX(0);
            }
            if (asteroid.getY() < 0) {
                asteroid.setY(gameData.getDisplayHeight());
            }
            if (asteroid.getY() > gameData.getDisplayHeight()) {
                asteroid.setY(0);
            }
        }
    }
}
