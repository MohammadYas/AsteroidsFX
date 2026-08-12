package dk.sdu.mmmi.mmy.main;

import dk.sdu.mmmi.mmy.common.services.IEntityProcessingService;
import dk.sdu.mmmi.mmy.common.services.IGamePluginService;
import dk.sdu.mmmi.mmy.common.services.IPostEntityProcessingService;
import javafx.application.Application;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.ServiceLoader;

public class Main extends Application {

    public static void main(String[] args) {
        launch(Main.class);
    }

    @Override
    public void start(Stage window) {
        Game game = new Game(
                load(IGamePluginService.class),
                load(IEntityProcessingService.class),
                load(IPostEntityProcessingService.class));

        game.start(window);
        game.render();
    }

    private <T> List<T> load(Class<T> service) {
        List<T> found = new ArrayList<>();
        ServiceLoader.load(service).forEach(found::add);
        return found;
    }
}
