package dk.sdu.mmmi.mmy.playersystem;

import dk.sdu.mmmi.mmy.common.data.GameData;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PlayerControlSystemTest {

    @Test
    void playerKeepsMovingWhenThrustIsReleased() {
        Player player = new Player();
        player.setX(100);
        player.setY(100);
        player.setDx(5);

        new PlayerControlSystem().move(player, new GameData());

        assertTrue(player.getX() > 100);
    }

    @Test
    void playerWrapsAroundTheLeftEdge() {
        GameData gameData = new GameData();

        Player player = new Player();
        player.setX(0);
        player.setY(100);
        player.setDx(-5);

        new PlayerControlSystem().move(player, gameData);

        assertEquals(gameData.getDisplayWidth(), player.getX(), 0.001);
    }
}
