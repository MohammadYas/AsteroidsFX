package dk.sdu.mmmi.mmy.main;

import dk.sdu.mmmi.mmy.common.services.IEntityProcessingService;
import dk.sdu.mmmi.mmy.common.services.IGamePluginService;
import dk.sdu.mmmi.mmy.common.services.IPostEntityProcessingService;
import dk.sdu.mmmi.mmy.common.util.ServiceLocator;
import javafx.application.Application;
import javafx.stage.Stage;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main extends Application {

    public static void main(String[] args) {
        launch(Main.class);
    }

    @Override
    public void start(Stage window) {
        Game game = useSpring() ? gameFromSpring() : gameFromServiceLoader();
        game.start(window);
        game.render();
    }

    private boolean useSpring() {
        return "spring".equalsIgnoreCase(System.getProperty("bootstrap"));
    }

    private Game gameFromServiceLoader() {
        System.out.println("Assembling with ServiceLoader");
        ServiceLocator locator = ServiceLocator.INSTANCE;
        return new Game(
                locator.locateAll(IGamePluginService.class),
                locator.locateAll(IEntityProcessingService.class),
                locator.locateAll(IPostEntityProcessingService.class));
    }

    private Game gameFromSpring() {
        System.out.println("Assembling with the Spring container");
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(ModuleConfig.class);
        return context.getBean(Game.class);
    }
}
