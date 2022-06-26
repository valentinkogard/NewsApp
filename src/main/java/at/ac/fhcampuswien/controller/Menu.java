package at.ac.fhcampuswien.controller;

import at.ac.fhcampuswien.downloader.MainDownload;
import at.ac.fhcampuswien.enumparams.Category;
import at.ac.fhcampuswien.enumparams.Endpoint;
import at.ac.fhcampuswien.enumparams.Sortby;
import at.ac.fhcampuswien.news.NewsApiException;
import at.ac.fhcampuswien.publisher.Channel;
import at.ac.fhcampuswien.publisher.Subscriber;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Menu extends Application {

    private final Channel channel = Channel.getInstance();
    private final Subscriber subscriber = new Subscriber();

    @FXML protected Label label;
    @FXML protected CheckBox cb;
    @FXML protected VBox vb;
    @FXML protected HBox hb;

    @FXML protected ComboBox endpointBox;
    @FXML protected ComboBox categoryBox;
    @FXML protected ComboBox sortbyBox;
    @FXML protected TextField countryText;
    @FXML protected TextField languageText;
    @FXML protected TextField qText;

    @FXML protected Text categoryLabel;
    @FXML protected Text languageLabel;
    @FXML protected Text sortbyLabel;
    @FXML protected Text countryLabel;
    @FXML protected Text qLabel;

    {
        channel.subscriber(subscriber);
        channel.setMenu(this);
    }

    @Override
    public void start(Stage stage) {
        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("main.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
        scene.getStylesheets().addAll(this.getClass().getResource("style.css").toExternalForm());
        stage.setTitle("NewsApp");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
        Button a = (Button) fxmlLoader.getNamespace().get("a");
        a.setOnAction(e -> {
            handleInput("a");
        });
        Button b = (Button) fxmlLoader.getNamespace().get("b");
        b.setOnAction(e -> {
            handleInput("b");
        });
        Button y = (Button) fxmlLoader.getNamespace().get("y");
        y.setOnAction(e -> {
            handleInput("y");
        });
        Button q = (Button) fxmlLoader.getNamespace().get("q");
        q.setOnAction(e -> {
            handleInput("q");
        });
        Button go = (Button) fxmlLoader.getNamespace().get("advancedGo");
        go.setOnAction(e-> {
            handleInput("g");
        });
        label = (Label) fxmlLoader.getNamespace().get("outputText");

        categoryBox = (ComboBox) fxmlLoader.getNamespace().get("categoryBox");
        List<String> categoryList = new ArrayList<>();
        for (Category cat : Category.values()) {
            //System.out.println(cat);
            categoryList.add(cat.value);
        }
        categoryBox.getItems().addAll(categoryList);
        categoryBox.getSelectionModel().selectFirst();

        sortbyBox = (ComboBox) fxmlLoader.getNamespace().get("sortbyBox");
        List<String> sortbyList = new ArrayList<>();
        for (Sortby by : Sortby.values()) {
            //System.out.println(by);
            sortbyList.add(by.value);
        }
        sortbyBox.getItems().addAll(sortbyList);
        sortbyBox.getSelectionModel().selectFirst();

        countryText = (TextField) fxmlLoader.getNamespace().get("countryText");
        languageText = (TextField) fxmlLoader.getNamespace().get("languageText");
        qText = (TextField) fxmlLoader.getNamespace().get("qText");

        endpointBox = (ComboBox) fxmlLoader.getNamespace().get("endpointBox");
        List<String> endpointList = new ArrayList<>();
        for (Endpoint end : Endpoint.values()) {
            System.out.println(end);
            endpointList.add(end.value);
        }
        endpointBox.getItems().addAll(endpointList);
        //enpointBox.getSelectionModel().selectFirst();

        categoryLabel = (Text) fxmlLoader.getNamespace().get("categoryLabel");
        languageLabel = (Text) fxmlLoader.getNamespace().get("languageLabel");
        sortbyLabel = (Text) fxmlLoader.getNamespace().get("sortbyLabel");
        countryLabel = (Text) fxmlLoader.getNamespace().get("countryLabel");
        qLabel = (Text) fxmlLoader.getNamespace().get("qLabel");

        endpointBox.valueProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observableValue, Object o, Object t1) {
                if (endpointBox.getSelectionModel().getSelectedItem().toString().equals("top-headlines")) {
                    countryText.setVisible(true);
                    languageText.setVisible(false);
                    qText.setVisible(false);
                    categoryBox.setVisible(true);
                    sortbyBox.setVisible(false);

                    countryLabel.setVisible(true);
                    languageLabel.setVisible(false);
                    qLabel.setVisible(false);
                    categoryLabel.setVisible(true);
                    sortbyLabel.setVisible(false);
                } else if(endpointBox.getSelectionModel().getSelectedItem().toString().equals("everything")) {
                    countryText.setVisible(false);
                    languageText.setVisible(true);
                    qText.setVisible(true);
                    categoryBox.setVisible(false);
                    sortbyBox.setVisible(true);

                    countryLabel.setVisible(false);
                    languageLabel.setVisible(true);
                    qLabel.setVisible(true);
                    categoryLabel.setVisible(false);
                    sortbyLabel.setVisible(true);
                } else {
                    countryText.setVisible(true);
                    languageText.setVisible(true);
                    qText.setVisible(true);
                    categoryBox.setVisible(true);
                    sortbyBox.setVisible(true);

                    countryLabel.setVisible(true);
                    languageLabel.setVisible(true);
                    qLabel.setVisible(true);
                    categoryLabel.setVisible(true);
                    sortbyLabel.setVisible(true);
                }
            }
        });

        //switch between advanced and normal mode
        vb = (VBox) fxmlLoader.getNamespace().get("advancedOptions");
        hb = (HBox) fxmlLoader.getNamespace().get("generalOptions");
        cb = (CheckBox) fxmlLoader.getNamespace().get("standard");

        cb.setSelected(true);
        vb.setVisible(false);
        cb.setOnAction(e -> {
            if(cb.isSelected()){
                vb.setVisible(false);
                hb.setVisible(true);
            } else {
                vb.setVisible(true);
                hb.setVisible(false);
            }
        });
    }

    public void setOutputText(String text){
        label.setText("");
        label.setText(text);
    }

    private void handleInput(String input){
        try {
            switch (input.charAt(0)) {
                case 'a' -> Actions.getInstance().getTopHeadlinesAustria();
                case 'b' -> Actions.getInstance().getAllNewsBitcoin();
                case 'y' -> Actions.getInstance().getArticleCount();
                case 'q' -> Actions.getInstance().printExitMessage();
                case 'g' -> Actions.getInstance().getCustomHeadlines(this);
                default -> Actions.getInstance().printInvalidInputMessage();
            }
            MainDownload.getInstance().measureTimeOfDownload();
        } catch (NewsApiException e) {
            setOutputText(e.getMessage());
        } catch (Exception e) {
            setOutputText("Something went wrong\n" + e.getMessage());
        }
    }
}
