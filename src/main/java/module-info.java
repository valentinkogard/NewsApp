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
    exports at.ac.fhcampuswien.publisher;
    opens at.ac.fhcampuswien.publisher to com.google.gson, javafx.fxml;
    exports at.ac.fhcampuswien.news;
    opens at.ac.fhcampuswien.news to com.google.gson, javafx.fxml;
    exports at.ac.fhcampuswien.articleStructure;
    opens at.ac.fhcampuswien.articleStructure to com.google.gson, javafx.fxml;
    exports at.ac.fhcampuswien.analytics;
    opens at.ac.fhcampuswien.analytics to com.google.gson, javafx.fxml;
    exports at.ac.fhcampuswien.controller;
    opens at.ac.fhcampuswien.controller to com.google.gson, javafx.fxml;
}