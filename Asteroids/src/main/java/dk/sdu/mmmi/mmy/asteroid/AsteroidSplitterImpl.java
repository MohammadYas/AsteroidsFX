package dk.sdu.mmmi.mmy.asteroid;

import dk.sdu.mmmi.mmy.common.asteroids.Asteroid;
import dk.sdu.mmmi.mmy.common.asteroids.IAsteroidSplitter;
import dk.sdu.mmmi.mmy.common.data.Entity;
import dk.sdu.mmmi.mmy.common.data.World;

public class AsteroidSplitterImpl implements IAsteroidSplitter {

    private static final double MINIMUM_RADIUS = 6;
    private static final double SHRINK_FACTOR = 0.55;

    @Override
    public void createSplitAsteroid(Entity asteroid, World world) {
        world.removeEntity(asteroid);

        double newRadius = asteroid.getRadius() * SHRINK_FACTOR;
        if (newRadius < MINIMUM_RADIUS) {
            return;
        }

        for (int i = 0; i < 2; i++) {
            world.addEntity(createFragment(asteroid, newRadius, i == 0 ? 45 : -45));
        }
    }

    private Asteroid createFragment(Entity parent, double radius, double rotationOffset) {
        double scale = radius / 14.0;

        Asteroid fragment = new Asteroid();
        fragment.setPolygonCoordinates(
                -14 * scale, -8 * scale, -6 * scale, -14 * scale,
                6 * scale, -12 * scale, 14 * scale, -4 * scale,
                12 * scale, 8 * scale, 2 * scale, 14 * scale,
                -8 * scale, 12 * scale, -14 * scale, 4 * scale);
        fragment.setRadius(radius);
        fragment.setX(parent.getX());
        fragment.setY(parent.getY());
        fragment.setRotation(parent.getRotation() + rotationOffset);
        fragment.setSpeed(1.5);
        fragment.setHitPoints(1);
        return fragment;
    }
}
