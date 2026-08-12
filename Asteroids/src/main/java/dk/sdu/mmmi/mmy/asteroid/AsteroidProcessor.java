package dk.sdu.mmmi.mmy.asteroid;

import dk.sdu.mmmi.mmy.common.data.GameData;
import dk.sdu.mmmi.mmy.common.data.World;
import dk.sdu.mmmi.mmy.common.services.IEntityProcessingService;

public class AsteroidProcessor implements IEntityProcessingService {

    @Override
    public void process(GameData gameData, World world) {
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
