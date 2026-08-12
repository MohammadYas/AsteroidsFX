package dk.sdu.mmmi.mmy.common.util;

import java.lang.module.Configuration;
import java.lang.module.ModuleDescriptor;
import java.lang.module.ModuleFinder;
import java.lang.module.ModuleReference;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.ServiceLoader;

public enum ServiceLocator {

    INSTANCE;

    private final ModuleLayer layer;

    ServiceLocator() {
        this.layer = createLayer();
    }

    private ModuleLayer createLayer() {
        Path pluginsDirectory = Path.of("plugins");

        if (!Files.isDirectory(pluginsDirectory)) {
            return null;
        }

        ModuleFinder finder = ModuleFinder.of(pluginsDirectory);
        List<String> pluginNames = finder.findAll().stream()
                .map(ModuleReference::descriptor)
                .map(ModuleDescriptor::name)
                .toList();

        if (pluginNames.isEmpty()) {
            return null;
        }

        Configuration configuration = ModuleLayer.boot().configuration()
                .resolve(ModuleFinder.of(), finder, pluginNames);

        return ModuleLayer.boot()
                .defineModulesWithOneLoader(configuration, ClassLoader.getSystemClassLoader());
    }

    public <T> List<T> locateAll(Class<T> service) {
        List<T> found = new ArrayList<>();

        ServiceLoader.load(service).forEach(found::add);

        if (layer != null) {
            for (T provider : ServiceLoader.load(layer, service)) {
                boolean alreadyFound = found.stream()
                        .anyMatch(p -> p.getClass().getName().equals(provider.getClass().getName()));
                if (!alreadyFound) {
                    found.add(provider);
                }
            }
        }
        return found;
    }
}
