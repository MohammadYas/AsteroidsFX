package dk.sdu.mmmi.mmy.collisionsystem;

import dk.sdu.mmmi.mmy.common.asteroids.Asteroid;
import dk.sdu.mmmi.mmy.common.asteroids.IAsteroidSplitter;
import dk.sdu.mmmi.mmy.common.data.Entity;
import dk.sdu.mmmi.mmy.common.data.GameData;
import dk.sdu.mmmi.mmy.common.data.World;
import dk.sdu.mmmi.mmy.common.services.IPostEntityProcessingService;
import dk.sdu.mmmi.mmy.common.services.IScoreService;

import java.util.List;
import java.util.Optional;
import java.util.ServiceLoader;

public class CollisionDetector implements IPostEntityProcessingService {

    private static final int POINTS_PER_ASTEROID = 10;

    @Override
    public void process(GameData gameData, World world) {
        List<Entity> entities = List.copyOf(world.getEntities());

        for (int i = 0; i < entities.size(); i++) {
            for (int j = i + 1; j < entities.size(); j++) {
                Entity first = entities.get(i);
                Entity second = entities.get(j);

                if (collides(first, second)) {
                    handleHit(first, world);
                    handleHit(second, world);
                }
            }
        }
    }

    public boolean collides(Entity first, Entity second) {
        double dx = first.getX() - second.getX();
        double dy = first.getY() - second.getY();
        double distance = Math.sqrt(dx * dx + dy * dy);
        return distance < first.getRadius() + second.getRadius();
    }

    private void handleHit(Entity entity, World world) {
        if (!entity.takeDamage()) {
            return;
        }

        ModuleLayer layer = getClass().getModule().getLayer();
        Optional<IAsteroidSplitter> splitter = ServiceLoader.load(layer, IAsteroidSplitter.class).findFirst();
        if (entity instanceof Asteroid && splitter.isPresent()) {
            splitter.get().createSplitAsteroid(entity, world);
        } else {
            world.removeEntity(entity);
        }

        if (entity instanceof Asteroid) {
            awardPoints(layer);
        }
    }

    private void awardPoints(ModuleLayer layer) {
        Optional<IScoreService> scoreService = ServiceLoader.load(layer, IScoreService.class).findFirst();
        if (scoreService.isPresent()) {
            scoreService.get().addPoints(POINTS_PER_ASTEROID);
        }
    }
}
