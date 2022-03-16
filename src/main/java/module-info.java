module at.ac.fhcampuswien{
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.desktop;
    requires javafx.media;

    opens at.ac.fhcampuswien to javafx.fxml;
    exports at.ac.fhcampuswien;
}