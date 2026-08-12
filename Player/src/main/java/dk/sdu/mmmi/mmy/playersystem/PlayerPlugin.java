package dk.sdu.mmmi.mmy.playersystem;

import dk.sdu.mmmi.mmy.common.data.Entity;
import dk.sdu.mmmi.mmy.common.data.GameData;
import dk.sdu.mmmi.mmy.common.data.World;
import dk.sdu.mmmi.mmy.common.services.IGamePluginService;

public class PlayerPlugin implements IGamePluginService {

    private Entity player;

    @Override
    public void start(GameData gameData, World world) {
        player = createPlayer(gameData);
        world.addEntity(player);
    }

    static Player createPlayer(GameData gameData) {
        Player player = new Player();
        player.setPolygonCoordinates(-5, -5, 10, 0, -5, 5);
        player.setX(gameData.getDisplayWidth() / 2.0);
        player.setY(gameData.getDisplayHeight() / 2.0);
        player.setRotation(-90);
        player.setRadius(8);
        player.setHitPoints(3);
        return player;
    }

    @Override
    public void stop(GameData gameData, World world) {
        if (player != null) {
            world.removeEntity(player);
            player = null;
        }
    }
}
