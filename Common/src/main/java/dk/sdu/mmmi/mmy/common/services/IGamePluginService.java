package dk.sdu.mmmi.mmy.common.services;

import dk.sdu.mmmi.mmy.common.data.GameData;
import dk.sdu.mmmi.mmy.common.data.World;

/**
 * Installs and uninstalls a component's entities.
 */
public interface IGamePluginService {

    /**
     * Adds the entities belonging to this component to the world.
     *
     * Pre-condition: gameData and world are initialised and not null.
     * Post-condition: the entities owned by this component exist in world.
     */
    void start(GameData gameData, World world);

    /**
     * Removes the entities belonging to this component from the world.
     *
     * Pre-condition: gameData and world are initialised and not null.
     * Post-condition: no entity owned by this component remains in world.
     */
    void stop(GameData gameData, World world);
}
