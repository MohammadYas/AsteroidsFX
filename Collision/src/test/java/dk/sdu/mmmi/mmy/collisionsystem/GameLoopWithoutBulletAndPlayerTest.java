package dk.sdu.mmmi.mmy.collisionsystem;

import dk.sdu.mmmi.mmy.asteroid.AsteroidPlugin;
import dk.sdu.mmmi.mmy.asteroid.AsteroidProcessor;
import dk.sdu.mmmi.mmy.common.asteroids.Asteroid;
import dk.sdu.mmmi.mmy.common.data.Entity;
import dk.sdu.mmmi.mmy.common.data.GameData;
import dk.sdu.mmmi.mmy.common.data.World;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;

class GameLoopWithoutBulletAndPlayerTest {

    private static final int FRAMES = 100;

    @Test
    void asteroidsKeepMovingWhenBulletAndPlayerAreMissing() {
        GameData gameData = new GameData();
        World world = new World();

        new AsteroidPlugin().start(gameData, world);

        Map<String, double[]> startPositions = new HashMap<>();
        for (Asteroid asteroid : world.getEntities(Asteroid.class)) {
            startPositions.put(asteroid.getID(), new double[]{asteroid.getX(), asteroid.getY()});
        }

        AsteroidProcessor processor = new AsteroidProcessor();
        CollisionDetector collisionDetector = new CollisionDetector();

        for (int frame = 0; frame < FRAMES; frame++) {
            processor.process(gameData, world);
            collisionDetector.process(gameData, world);
        }

        int stillFlying = 0;
        for (Map.Entry<String, double[]> startPosition : startPositions.entrySet()) {
            Entity asteroid = world.getEntity(startPosition.getKey());
            if (asteroid == null) {
                continue;
            }
            stillFlying++;

            double[] start = startPosition.getValue();
            boolean moved = asteroid.getX() != start[0] || asteroid.getY() != start[1];
            assertTrue(moved, "asteroid did not move");
        }

        assertTrue(stillFlying > 0, "no asteroid survived the loop");
    }
}
