package dk.sdu.mmmi.mmy.playersystem;

import dk.sdu.mmmi.mmy.common.bullet.BulletSPI;
import dk.sdu.mmmi.mmy.common.data.GameData;
import dk.sdu.mmmi.mmy.common.data.GameKeys;
import dk.sdu.mmmi.mmy.common.data.World;
import dk.sdu.mmmi.mmy.common.services.IEntityProcessingService;

import java.util.ServiceLoader;

public class PlayerControlSystem implements IEntityProcessingService {

    private static final double TURN_SPEED = 2.5;
    private static final double THRUST = 0.1;
    private static final double FRICTION = 0.96;
    private static final double MAX_SPEED = 2.5;
    private static final int SHOT_DELAY = 15;

    private int framesSinceLastShot = SHOT_DELAY;

    @Override
    public void process(GameData gameData, World world) {
        framesSinceLastShot++;

        for (Player player : world.getEntities(Player.class)) {
            if (gameData.getKeys().isDown(GameKeys.LEFT)) {
                player.setRotation(player.getRotation() - TURN_SPEED);
            }
            if (gameData.getKeys().isDown(GameKeys.RIGHT)) {
                player.setRotation(player.getRotation() + TURN_SPEED);
            }
            if (gameData.getKeys().isDown(GameKeys.UP)) {
                double radians = Math.toRadians(player.getRotation());
                player.setDx(player.getDx() + Math.cos(radians) * THRUST);
                player.setDy(player.getDy() + Math.sin(radians) * THRUST);
            }
            if (gameData.getKeys().isDown(GameKeys.SPACE) && framesSinceLastShot >= SHOT_DELAY) {
                shoot(player, gameData, world);
            }

            move(player, gameData);
        }
    }

    void move(Player player, GameData gameData) {
        player.setDx(player.getDx() * FRICTION);
        player.setDy(player.getDy() * FRICTION);

        double speed = Math.sqrt(player.getDx() * player.getDx() + player.getDy() * player.getDy());
        if (speed > MAX_SPEED) {
            player.setDx(player.getDx() / speed * MAX_SPEED);
            player.setDy(player.getDy() / speed * MAX_SPEED);
        }

        player.setX(player.getX() + player.getDx());
        player.setY(player.getY() + player.getDy());

        if (player.getX() < 0) {
            player.setX(gameData.getDisplayWidth());
        }
        if (player.getX() > gameData.getDisplayWidth()) {
            player.setX(0);
        }
        if (player.getY() < 0) {
            player.setY(gameData.getDisplayHeight());
        }
        if (player.getY() > gameData.getDisplayHeight()) {
            player.setY(0);
        }
    }

    private void shoot(Player player, GameData gameData, World world) {
        ModuleLayer layer = getClass().getModule().getLayer();
        ServiceLoader.load(layer, BulletSPI.class).findFirst().ifPresent(spi -> {
            world.addEntity(spi.createBullet(player, gameData));
            framesSinceLastShot = 0;
        });
    }
}
