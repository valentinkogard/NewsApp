module at.ac.fhcampuswien{
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.desktop;
    requires javafx.media;
    requires okhttp3;
    requires com.google.gson;
    requires commons.lang3;

    opens at.ac.fhcampuswien to javafx.fxml, com.google.gson;
    exports at.ac.fhcampuswien;
    exports at.ac.fhcampuswien.enumparams;
    opens at.ac.fhcampuswien.enumparams to com.google.gson, javafx.fxml;
}