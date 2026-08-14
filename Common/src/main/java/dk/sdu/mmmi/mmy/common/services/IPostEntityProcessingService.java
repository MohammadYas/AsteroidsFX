package dk.sdu.mmmi.mmy.common.services;

import dk.sdu.mmmi.mmy.common.data.GameData;
import dk.sdu.mmmi.mmy.common.data.World;

public interface IPostEntityProcessingService {

    void process(GameData gameData, World world);
}
