module Core {
    requires Common;
    requires javafx.controls;
    requires javafx.graphics;
    requires spring.context;
    requires spring.core;
    requires spring.beans;

    uses dk.sdu.mmmi.mmy.common.services.IGamePluginService;
    uses dk.sdu.mmmi.mmy.common.services.IEntityProcessingService;
    uses dk.sdu.mmmi.mmy.common.services.IPostEntityProcessingService;

    exports dk.sdu.mmmi.mmy.main;
    opens dk.sdu.mmmi.mmy.main to javafx.graphics, spring.core, spring.beans, spring.context;
}
