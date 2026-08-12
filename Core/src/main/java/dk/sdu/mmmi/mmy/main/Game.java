package dk.sdu.mmmi.mmy.main;

import dk.sdu.mmmi.mmy.common.data.Entity;
import dk.sdu.mmmi.mmy.common.data.GameData;
import dk.sdu.mmmi.mmy.common.data.GameKeys;
import dk.sdu.mmmi.mmy.common.data.World;
import dk.sdu.mmmi.mmy.common.services.IEntityProcessingService;
import dk.sdu.mmmi.mmy.common.services.IGamePluginService;
import dk.sdu.mmmi.mmy.common.services.IPostEntityProcessingService;
import dk.sdu.mmmi.mmy.common.services.IScoreService;
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Game {

    private final GameData gameData = new GameData();
    private final World world = new World();
    private final Map<Entity, Polygon> polygons = new ConcurrentHashMap<>();
    private final Pane gameWindow = new Pane();
    private final Text status = new Text(10, 20, "");

    private static final int SCORE_REFRESH_DELAY = 60;
    private static final long UPDATE_INTERVAL = 1_000_000_000L / 60;

    private final List<IGamePluginService> gamePluginServices;
    private final List<IEntityProcessingService> entityProcessingServices;
    private final List<IPostEntityProcessingService> postEntityProcessingServices;
    private final List<IScoreService> scoreServices;

    private int framesSinceScoreRefresh = 0;

    public Game(List<IGamePluginService> gamePluginServices,
                List<IEntityProcessingService> entityProcessingServices,
                List<IPostEntityProcessingService> postEntityProcessingServices,
                List<IScoreService> scoreServices) {
        this.gamePluginServices = gamePluginServices;
        this.entityProcessingServices = entityProcessingServices;
        this.postEntityProcessingServices = postEntityProcessingServices;
        this.scoreServices = scoreServices;
    }

    public void start(Stage window) {
        gameWindow.setPrefSize(gameData.getDisplayWidth(), gameData.getDisplayHeight());
        gameWindow.setStyle("-fx-background-color: black;");

        status.setFill(Color.LIGHTGRAY);
        status.setText(statusText());
        gameWindow.getChildren().add(status);

        Scene scene = new Scene(gameWindow);
        scene.setOnKeyPressed(event -> setKey(event.getCode(), true));
        scene.setOnKeyReleased(event -> setKey(event.getCode(), false));

        for (IGamePluginService plugin : gamePluginServices) {
            plugin.start(gameData, world);
        }

        window.setScene(scene);
        window.setTitle("ASTEROIDS");
        window.show();
    }

    private String statusText() {
        String text = "Loaded components: " + gamePluginServices.size() + " plugins, "
                + entityProcessingServices.size() + " processors, "
                + postEntityProcessingServices.size() + " post-processors";

        if (!scoreServices.isEmpty()) {
            text = text + " - score: " + scoreServices.get(0).getScore();
        }
        return text;
    }

    private void setKey(KeyCode code, boolean pressed) {
        if (code == KeyCode.LEFT) {
            gameData.getKeys().setKey(GameKeys.LEFT, pressed);
        }
        if (code == KeyCode.RIGHT) {
            gameData.getKeys().setKey(GameKeys.RIGHT, pressed);
        }
        if (code == KeyCode.UP) {
            gameData.getKeys().setKey(GameKeys.UP, pressed);
        }
        if (code == KeyCode.SPACE) {
            gameData.getKeys().setKey(GameKeys.SPACE, pressed);
        }
    }

    public void render() {
        new AnimationTimer() {
            private long nextUpdate = 0;

            @Override
            public void handle(long now) {
                if (now >= nextUpdate) {
                    update();
                    nextUpdate = now + UPDATE_INTERVAL;
                }
                draw();
            }
        }.start();
    }

    private void update() {
        for (IEntityProcessingService processor : entityProcessingServices) {
            processor.process(gameData, world);
        }
        for (IPostEntityProcessingService processor : postEntityProcessingServices) {
            processor.process(gameData, world);
        }

        framesSinceScoreRefresh++;
        if (framesSinceScoreRefresh >= SCORE_REFRESH_DELAY) {
            framesSinceScoreRefresh = 0;
            status.setText(statusText());
        }
    }

    private void draw() {
        for (Entity entity : polygons.keySet()) {
            if (world.getEntity(entity.getID()) == null) {
                gameWindow.getChildren().remove(polygons.remove(entity));
            }
        }

        for (Entity entity : world.getEntities()) {
            Polygon polygon = polygons.get(entity);
            if (polygon == null) {
                polygon = new Polygon(entity.getPolygonCoordinates());
                polygon.setStroke(Color.WHITE);
                polygon.setFill(Color.TRANSPARENT);
                polygons.put(entity, polygon);
                gameWindow.getChildren().add(polygon);
            }
            polygon.setTranslateX(entity.getX());
            polygon.setTranslateY(entity.getY());
            polygon.setRotate(entity.getRotation());
        }
    }
}
