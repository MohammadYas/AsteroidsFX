module Core {
    requires Common;
    requires javafx.controls;
    requires javafx.graphics;

    exports dk.sdu.mmmi.mmy.main;
    opens dk.sdu.mmmi.mmy.main to javafx.graphics;
}
