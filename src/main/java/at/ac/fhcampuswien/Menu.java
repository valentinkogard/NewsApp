package at.ac.fhcampuswien;

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

import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Menu extends Application {

    private final AppController controller = new AppController();
    private static final String INVALID_INPUT_MESSAGE = "Invalid input! Please enter an existing option!";
    private static final String EXIT_MESSAGE = "Bye bye!";

    @FXML protected Label label;
    @FXML protected CheckBox cb;
    @FXML protected VBox vb;
    @FXML protected HBox hb;

    protected FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("main.fxml"));

    @Override
    public void start(Stage stage) throws Exception {
        //FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("main.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        scene.getStylesheets().addAll(this.getClass().getResource("style.css").toExternalForm());
        stage.setTitle("NewsApp");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
        Button a = (Button) fxmlLoader.getNamespace().get("a");
        a.setOnAction(e -> {
            try {
                handleInput("a");
            } catch (NewsApiException n) {
                n.getMessage();
            }
        });
        Button b = (Button) fxmlLoader.getNamespace().get("b");
        b.setOnAction(e -> {
            try {
                handleInput("b");
            } catch (NewsApiException n) {
                n.getMessage();
            }
        });
        Button y = (Button) fxmlLoader.getNamespace().get("y");
        y.setOnAction(e -> {
            try {
                handleInput("y");
            } catch (NewsApiException n) {
                n.getMessage();
            }
        });
        Button q = (Button) fxmlLoader.getNamespace().get("q");
        q.setOnAction(e -> {
            try {
                handleInput("q");
            } catch (NewsApiException n) {
                n.getMessage();
            }
        });
        Button go = (Button) fxmlLoader.getNamespace().get("advancedGo");
        go.setOnAction(e-> {
            try {

                handleInput("g");

            } catch (NewsApiException n) {
                n.getMessage();
            }
        });
        label = (Label) fxmlLoader.getNamespace().get("outputText");

        ComboBox categoryBox = (ComboBox) fxmlLoader.getNamespace().get("categoryBox");
        List<String> categoryList = new ArrayList<>();
        for (Category cat : Category.values()) {
            //System.out.println(cat);
            categoryList.add(cat.value);
        }
        categoryBox.getItems().addAll(categoryList);
        //categoryBox.setOnAction(e -> {
            //System.out.println("-------------------------");
            //System.out.println(categoryBox);
        //});
        categoryBox.getSelectionModel().selectFirst();

        ComboBox sortbyBox = (ComboBox) fxmlLoader.getNamespace().get("sortbyBox");
        List<String> sortbyList = new ArrayList<>();
        for (Sortby by : Sortby.values()) {
            //System.out.println(by);
            sortbyList.add(by.value);
        }
        sortbyBox.getItems().addAll(sortbyList);
        sortbyBox.getSelectionModel().selectFirst();

        TextField countryText = (TextField) fxmlLoader.getNamespace().get("countryText");
        TextField languageText = (TextField) fxmlLoader.getNamespace().get("languageText");

        ComboBox enpointBox = (ComboBox) fxmlLoader.getNamespace().get("endpointBox");
        List<String> endpointList = new ArrayList<>();
        for (Endpoint end : Endpoint.values()) {
            System.out.println(end);
            endpointList.add(end.value);
        }
        enpointBox.getItems().addAll(endpointList);
        //enpointBox.getSelectionModel().selectFirst();

        Text categoryLabel = (Text) fxmlLoader.getNamespace().get("categoryLabel");
        Text languageLabel = (Text) fxmlLoader.getNamespace().get("languageLabel");
        Text sortbyLabel = (Text) fxmlLoader.getNamespace().get("sortbyLabel");
        Text countryLabel = (Text) fxmlLoader.getNamespace().get("countryLabel");


        enpointBox.valueProperty().addListener(new ChangeListener() {
           @Override
           public void changed(ObservableValue observableValue, Object o, Object t1) {
               if (enpointBox.getSelectionModel().getSelectedItem().toString().equals("top-headlines")) {
                   countryText.setVisible(true);
                   languageText.setVisible(false);
                   categoryBox.setVisible(true);
                   sortbyBox.setVisible(false);

                   countryLabel.setVisible(true);
                   languageLabel.setVisible(false);
                   categoryLabel.setVisible(true);
                   sortbyLabel.setVisible(false);
               } else if(enpointBox.getSelectionModel().getSelectedItem().toString().equals("everything")) {
                   countryText.setVisible(false);
                   languageText.setVisible(true);
                   categoryBox.setVisible(false);
                   sortbyBox.setVisible(true);

                   countryLabel.setVisible(false);
                   languageLabel.setVisible(true);
                   categoryLabel.setVisible(false);
                   sortbyLabel.setVisible(true);
               } else {
                   countryText.setVisible(true);
                   languageText.setVisible(true);
                   categoryBox.setVisible(true);
                   sortbyBox.setVisible(true);

                   countryLabel.setVisible(true);
                   languageLabel.setVisible(true);
                   categoryLabel.setVisible(true);
                   sortbyLabel.setVisible(true);
               }
           }
       });




        vb = (VBox) fxmlLoader.getNamespace().get("advancedOptions");
        hb = (HBox) fxmlLoader.getNamespace().get("generalOptions");
        cb = (CheckBox) fxmlLoader.getNamespace().get("standard");

        cb.setSelected(true);
        vb.setVisible(false);
        cb.setOnAction(e -> {
            if(cb.isSelected()){
                vb.setVisible(false);
                hb.setVisible(true);
            }
            else {
                vb.setVisible(true);
                hb.setVisible(false);

            }
        });
    }




    public void setOutputText(String text){
        label.setText("");
        label.setText(text);
    }

    public void handleInput(String input) throws NewsApiException {
        try {
            switch (input.charAt(0)) {
                case 'a' -> getTopHeadlinesAustria(controller);
                case 'b' -> getAllNewsBitcoin(controller);
                case 'y' -> getArticleCount(controller);
                case 'q' -> printExitMessage();
                case 'g' -> getCustomHeadlines(controller);
                default -> printInvalidInputMessage();
            }
        }
        catch (NewsApiException n) {

            throw new NewsApiException("Something went wrong");

            }

    }
        /*switch (input.charAt(0)) {
            case 'a' -> getTopHeadlinesAustria(controller);
            case 'b' -> getAllNewsBitcoin(controller);
            case 'y' -> getArticleCount(controller);
            case 'q' -> printExitMessage();
            default -> printInvalidInputMessage();
        }*/



    private void getArticleCount(AppController ctrl) {
        //System.out.println("Number of articles: " + ctrl.getArticleCount());

        int austriaCount = 0;
        int bitcoinCount = 0;

        try {
            austriaCount = ctrl.getTopHeadlinesAustria().getArticles().size();
        } catch (NewsApiException e) {
            austriaCount = 0;
            //e.printStackTrace();
        }
        try {
            bitcoinCount = ctrl.getAllNewsBitcoin().getArticles().size();
        } catch (NewsApiException e) {
            bitcoinCount = 0;
            //e.printStackTrace();
        }

        try {

            List<Article> articleList = ctrl.getAllNewsBitcoin().getArticles();
            StreamFilters streamFilter = new StreamFilters();

            setOutputText("Number of articles: " + (austriaCount + bitcoinCount) + "\n" +
                    "Most articles provided by: " + streamFilter.sourceWithMostArticles(articleList) + "\n" +
                    "Author with longest name: " + streamFilter.authorWithLongestName(articleList) + "\n" +
                    "Number of articles by Reuters: " + streamFilter.numberOfArticlesNyt(articleList) + "\n" +
                    "Articles with headlines less than 15 characters: " + streamFilter.articlesWithHeadlineSub15Chars(articleList) + "\n" +
                    formatOutput(streamFilter.articlesWithHeadlineSub15CharsList(articleList)));

        } catch (NewsApiException n) {
            NewsApiException e = new NewsApiException("Statistics could not be fully loaded.");
            setOutputText(e.getMessage());
        }
    }

    private void getTopHeadlinesAustria(AppController ctrl) throws NewsApiException {
        //System.out.println(ctrl.getTopHeadlinesAustria());
        //setOutputText(formatOutput(ctrl.getTopHeadlinesAustria()));

        if (ctrl.getTopHeadlinesAustria().getArticles().size()==0) {
            //setOutputText("No articles available for this query");
            NewsApiException e = new NewsApiException("No articles available for this query");
            setOutputText(e.getMessage());

        } else {


            try {
                setOutputText(formatOutput(ctrl.getTopHeadlinesAustria().getArticles()));
            } catch (NewsApiException e) {
                setOutputText(e.getMessage());
                //ioe.printStackTrace();
            }
        }
    }


    private void getAllNewsBitcoin(AppController ctrl) throws NewsApiException {
        //System.out.println(ctrl.getAllNewsBitcoin());
        //setOutputText(ctrl.getAllNewsBitcoin().toString());
        //setOutputText(formatOutput(ctrl.getAllNewsBitcoin()));
        try {
            //setOutputText(formatOutput(ctrl.getAllNewsBitcoin().getArticles()));
            setOutputText(formatOutput(new StreamFilters().articlesSortedByLengthThenAlphabetically(ctrl.getAllNewsBitcoin().getArticles())));
            /*System.out.println(new StreamFilters().numberOfArticlesNyt(ctrl.getAllNewsBitcoin().getArticles()));
            System.out.println(new StreamFilters().articlesWithHeadlineSub15Chars(ctrl.getAllNewsBitcoin().getArticles()));
            setOutputText(formatOutput(new StreamFilters().articlesSortedByLengthThenAlphabetically(ctrl.getAllNewsBitcoin().getArticles())));
            System.out.println(new StreamFilters().authorWithLongestName(ctrl.getAllNewsBitcoin().getArticles()));
            System.out.println(new StreamFilters().sourceWithMostArticles(ctrl.getAllNewsBitcoin().getArticles()));
            */
        } catch (NewsApiException e) {
            setOutputText(e.getMessage());
        }
    }

    public void getCustomHeadlines(AppController ctrl) throws NewsApiException {

        try {
            setOutputText(formatOutput(ctrl.getCustomHeadlines(fxmlLoader).getArticles()));
        } catch (NewsApiException e) {
            setOutputText(e.getMessage());
        }
    }

    private String formatOutput(List<Article> list){
        String text = "";

        try {
            for(int i = 0; i < list.size(); i++) {



            text += "\n";
            text += list.get(i);
            text += list.get(i).getDescription();
            text += "\n";

        } } catch (NullPointerException n) {
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
