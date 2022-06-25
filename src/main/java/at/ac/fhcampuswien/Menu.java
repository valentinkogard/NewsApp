package at.ac.fhcampuswien;

import at.ac.fhcampuswien.downloader.MainDownload;
import at.ac.fhcampuswien.enumparams.Category;
import at.ac.fhcampuswien.enumparams.Endpoint;
import at.ac.fhcampuswien.enumparams.Sortby;
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

    private final AppController controller = AppController.getInstance();
    private static final String INVALID_INPUT_MESSAGE = "Invalid input! Please enter an existing option!";
    private static final String EXIT_MESSAGE = "Bye bye!";

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

    @Override
    public void start(Stage stage) {
        //!!!!!!!!!!!!!!!!!SINGELTON???????!!!!!!!!!!!!!!!!1
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("main.fxml"));
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

    private void setOutputText(String text){
        label.setText("");
        label.setText(text);
    }

    public void handleInput(String input){
        try {
            switch (input.charAt(0)) {
                case 'a' -> getTopHeadlinesAustria(controller);
                case 'b' -> getAllNewsBitcoin(controller);
                case 'y' -> getArticleCount(controller);
                case 'q' -> printExitMessage();
                case 'g' -> getCustomHeadlines(controller);
                default -> printInvalidInputMessage();
            }
            MainDownload.getInstance().measureTimeOfDownload();
        } catch (NewsApiException e) {
            setOutputText(e.getMessage());
        } catch (Exception e) {
            setOutputText("Something went wrong\n" + e.getMessage());
        }
    }

    private void getArticleCount(AppController ctrl) throws NewsApiException {
        int count = ctrl.getArticles().size();
        List<Article> articleList = ctrl.getArticles();
        StreamFilters streamFilter = StreamFilters.getInstance();
        try{
            setOutputText("Number of articles: " + count + "\n" +
                    "Most articles provided by: " + streamFilter.sourceWithMostArticles(articleList) + "\n" +
                    "Author with longest name: " + streamFilter.authorWithLongestName(articleList) + "\n" +
                    "Number of articles by NYT: " + streamFilter.numberOfArticlesNyt(articleList) + "\n" +
                    "Number of articles with headlines less than 15 characters: " + streamFilter.articlesWithHeadlineSub15Chars(articleList) + "\n" +
                    "Headlines with less than 15 characters: " + formatOutput(streamFilter.articlesWithHeadlineSub15CharsList(articleList)));
        } catch (NullPointerException e){
            throw new NewsApiException("Request articles before use this operation");
        }
    }

    private void getTopHeadlinesAustria(AppController ctrl) throws NewsApiException {
        List<Article> articles = ctrl.getTopHeadlinesAustria().getArticles();
        setOutputText(formatOutput(articles));
        ctrl.setArticles(articles);
    }

    private void getAllNewsBitcoin(AppController ctrl) throws NewsApiException {
        List<Article> articles = ctrl.getAllNewsBitcoin().getArticles();
        setOutputText(formatOutput(StreamFilters.getInstance().articlesSortedByLengthThenAlphabetically(articles)));
        ctrl.setArticles(articles);
    }

    public void getCustomHeadlines(AppController ctrl) throws NewsApiException {
        List<Article> articles = ctrl.getCustomHeadlines(this).getArticles();
        setOutputText(formatOutput(articles));
        ctrl.setArticles(articles);
    }

    private String formatOutput(List<Article> list){
        String text = "";
        try {
            for(int i = 0; i < list.size(); i++) {
                text += "\n";
                text += list.get(i);
                text += list.get(i).getDescription();
                text += "\n";
            }
        } catch (NullPointerException n) {
            n.getMessage();
        }
        return text;
    }

    private static void printExitMessage() {
        System.out.println(EXIT_MESSAGE);
        System.exit(0);
    }

    private static void printInvalidInputMessage() {
        System.out.println(INVALID_INPUT_MESSAGE);
    }

    private static void printMenu(){
        System.out.print("****************************** \n" +
                "*  Welcome to NewsApp  * \n" +
                "****************************** \n" +
                "Enter what you want to do: \n"+
                "a: Get top headlines Austria \n"+
                "b: Get all news about bitcoin \n"+
                "y: Count articles \n"+
                "q: quit program\n");
    }
}
