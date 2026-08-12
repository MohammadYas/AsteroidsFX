package dk.sdu.mmmi.mmy.collisionsystem;

import dk.sdu.mmmi.mmy.common.data.Entity;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CollisionDetectorTest {

    private Entity entityAt(double x, double y, double radius) {
        Entity entity = new Entity();
        entity.setX(x);
        entity.setY(y);
        entity.setRadius(radius);
        return entity;
    }

    @Test
    void overlappingEntitiesCollide() {
        CollisionDetector detector = new CollisionDetector();
        assertTrue(detector.collides(entityAt(100, 100, 10), entityAt(105, 100, 10)));
    }

    @Test
    void distantEntitiesDoNotCollide() {
        CollisionDetector detector = new CollisionDetector();
        assertFalse(detector.collides(entityAt(0, 0, 5), entityAt(200, 200, 5)));
    }

    @Test
    void entitiesTouchingAtTheEdgeDoNotCollide() {
        CollisionDetector detector = new CollisionDetector();
        assertFalse(detector.collides(entityAt(0, 0, 10), entityAt(20, 0, 10)));
    }
}
