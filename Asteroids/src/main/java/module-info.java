import dk.sdu.mmmi.mmy.common.asteroids.IAsteroidSplitter;
import dk.sdu.mmmi.mmy.common.services.IEntityProcessingService;
import dk.sdu.mmmi.mmy.common.services.IGamePluginService;

module Asteroids {
    requires Common;
    requires CommonAsteroids;

    provides IGamePluginService with dk.sdu.mmmi.mmy.asteroid.AsteroidPlugin;
    provides IEntityProcessingService with dk.sdu.mmmi.mmy.asteroid.AsteroidProcessor;
    provides IAsteroidSplitter with dk.sdu.mmmi.mmy.asteroid.AsteroidSplitterImpl;

    exports dk.sdu.mmmi.mmy.asteroid;
}
