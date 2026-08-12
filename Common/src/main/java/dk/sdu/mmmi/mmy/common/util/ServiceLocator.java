package dk.sdu.mmmi.mmy.common.util;

import java.util.ArrayList;
import java.util.List;
import java.util.ServiceLoader;

public enum ServiceLocator {

    INSTANCE;

    public <T> List<T> locateAll(Class<T> service) {
        List<T> found = new ArrayList<>();

        ServiceLoader.load(service).forEach(found::add);

        return found;
    }
}
