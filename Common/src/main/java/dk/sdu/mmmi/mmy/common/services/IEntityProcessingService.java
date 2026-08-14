package dk.sdu.mmmi.mmy.common.services;

import dk.sdu.mmmi.mmy.common.data.GameData;
import dk.sdu.mmmi.mmy.common.data.World;

/**
 * Updates a component's own entities once per frame.
 */
public interface IEntityProcessingService {

    /**
     * Advances this component's entities by one frame.
     *
     * Pre-condition: gameData and world are not null.
     * Post-condition: the entities owned by this component have been updated.
     */
    void process(GameData gameData, World world);
}
