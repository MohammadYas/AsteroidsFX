package dk.sdu.mmmi.mmy.main;

import dk.sdu.mmmi.mmy.common.services.IEntityProcessingService;
import dk.sdu.mmmi.mmy.common.services.IGamePluginService;
import dk.sdu.mmmi.mmy.common.services.IPostEntityProcessingService;
import dk.sdu.mmmi.mmy.common.util.ServiceLocator;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(Main.class);
    }

    @Override
    public void start(Stage window) {
        ServiceLocator locator = ServiceLocator.INSTANCE;

        Game game = new Game(
                locator.locateAll(IGamePluginService.class),
                locator.locateAll(IEntityProcessingService.class),
                locator.locateAll(IPostEntityProcessingService.class));

        game.start(window);
        game.render();
    }
}
