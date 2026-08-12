package dk.sdu.mmmi.mmy.asteroid;

import dk.sdu.mmmi.mmy.common.data.GameData;
import dk.sdu.mmmi.mmy.common.data.World;
import dk.sdu.mmmi.mmy.common.services.IGamePluginService;

import java.util.Random;

public class AsteroidPlugin implements IGamePluginService {

    private static final int ASTEROID_COUNT = 5;

    private final Random random = new Random();

    @Override
    public void start(GameData gameData, World world) {
        for (int i = 0; i < ASTEROID_COUNT; i++) {
            world.addEntity(createAsteroid(gameData));
        }
    }

    private Asteroid createAsteroid(GameData gameData) {
        Asteroid asteroid = new Asteroid();
        asteroid.setPolygonCoordinates(-14, -8, -6, -14, 6, -12, 14, -4, 12, 8, 2, 14, -8, 12, -14, 4);
        asteroid.setRadius(14);
        asteroid.setHitPoints(1);
        asteroid.setSpeed(0.5 + random.nextDouble());
        asteroid.setRotation(random.nextInt(360));

        if (random.nextBoolean()) {
            asteroid.setX(random.nextBoolean() ? 0 : gameData.getDisplayWidth());
            asteroid.setY(random.nextInt(gameData.getDisplayHeight()));
        } else {
            asteroid.setX(random.nextInt(gameData.getDisplayWidth()));
            asteroid.setY(random.nextBoolean() ? 0 : gameData.getDisplayHeight());
        }
        return asteroid;
    }

    @Override
    public void stop(GameData gameData, World world) {
        for (Asteroid asteroid : world.getEntities(Asteroid.class)) {
            world.removeEntity(asteroid);
        }
    }
}
