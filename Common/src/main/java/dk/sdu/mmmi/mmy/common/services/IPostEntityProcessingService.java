package dk.sdu.mmmi.mmy.common.services;

import dk.sdu.mmmi.mmy.common.data.GameData;
import dk.sdu.mmmi.mmy.common.data.World;

/**
 * Runs after every entity has been updated. Used for logic that needs the
 * final positions of the frame, such as collision detection.
 */
public interface IPostEntityProcessingService {

    /**
     * Applies logic across all entities in the world.
     *
     * Pre-condition: every IEntityProcessingService has already run this frame.
     * Post-condition: global rules have been applied to the world.
     */
    void process(GameData gameData, World world);
}
