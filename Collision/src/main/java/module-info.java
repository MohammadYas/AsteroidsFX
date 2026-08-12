import dk.sdu.mmmi.mmy.common.services.IPostEntityProcessingService;

module Collision {
    requires Common;
    requires CommonAsteroids;

    uses dk.sdu.mmmi.mmy.common.asteroids.IAsteroidSplitter;
    uses dk.sdu.mmmi.mmy.common.services.IScoreService;

    provides IPostEntityProcessingService with dk.sdu.mmmi.mmy.collisionsystem.CollisionDetector;

    exports dk.sdu.mmmi.mmy.collisionsystem;
}
