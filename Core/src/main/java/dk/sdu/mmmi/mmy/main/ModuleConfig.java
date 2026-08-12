package dk.sdu.mmmi.mmy.main;

import dk.sdu.mmmi.mmy.common.services.IEntityProcessingService;
import dk.sdu.mmmi.mmy.common.services.IGamePluginService;
import dk.sdu.mmmi.mmy.common.services.IPostEntityProcessingService;
import dk.sdu.mmmi.mmy.common.services.IScoreService;
import dk.sdu.mmmi.mmy.common.util.ServiceLocator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class ModuleConfig {

    @Bean
    public List<IGamePluginService> gamePluginServices() {
        return ServiceLocator.INSTANCE.locateAll(IGamePluginService.class);
    }

    @Bean
    public List<IEntityProcessingService> entityProcessingServices() {
        return ServiceLocator.INSTANCE.locateAll(IEntityProcessingService.class);
    }

    @Bean
    public List<IPostEntityProcessingService> postEntityProcessingServices() {
        return ServiceLocator.INSTANCE.locateAll(IPostEntityProcessingService.class);
    }

    @Bean
    public List<IScoreService> scoreServices() {
        return ServiceLocator.INSTANCE.locateAll(IScoreService.class);
    }

    @Bean
    public Game game(List<IGamePluginService> gamePluginServices,
                     List<IEntityProcessingService> entityProcessingServices,
                     List<IPostEntityProcessingService> postEntityProcessingServices,
                     List<IScoreService> scoreServices) {
        return new Game(gamePluginServices, entityProcessingServices,
                postEntityProcessingServices, scoreServices);
    }
}
