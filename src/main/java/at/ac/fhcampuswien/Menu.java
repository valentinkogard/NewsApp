package at.ac.fhcampuswien;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class Menu extends Application {

    private final AppController controller = new AppController();
    private static final String INVALID_INPUT_MESSAGE = "Invalid input! Please enter an existing option!";
    private static final String EXIT_MESSAGE = "Bye bye!";

    protected Label label;

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("main.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
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
        label = (Label) fxmlLoader.getNamespace().get("outputText");
    }

    public void setOutputText(String text){
        label.setText(text);
    }

    public void handleInput(String input) {
        switch (input.charAt(0)) {
            case 'a' -> getTopHeadlinesAustria(controller);
            case 'b' -> getAllNewsBitcoin(controller);
            case 'y' -> getArticleCount(controller);
            case 'q' -> printExitMessage();
            default -> printInvalidInputMessage();
        }
    }

    private void getArticleCount(AppController ctrl) {
        //System.out.println("Number of articles: " + ctrl.getArticleCount());

        int austriaCount = 0;
        int bitcoinCount = 0;

        try {
            austriaCount = ctrl.getTopHeadlinesAustria().getArticles().size();
        } catch (IOException e) {
            austriaCount = 0;
            //e.printStackTrace();
        }
        try {
            bitcoinCount = ctrl.getAllNewsBitcoin().getArticles().size();
        } catch (IOException e) {
            bitcoinCount = 0;
            //e.printStackTrace();
        }

        setOutputText("Number of articles: " + (austriaCount + bitcoinCount));
    }

    private void getTopHeadlinesAustria(AppController ctrl) {
        //System.out.println(ctrl.getTopHeadlinesAustria());
        //setOutputText(formatOutput(ctrl.getTopHeadlinesAustria()));
        try {
            setOutputText(formatOutput(ctrl.getTopHeadlinesAustria().getArticles()));
        } catch (IOException ioe) {
            setOutputText("Couldn't get articles from API...");
            //ioe.printStackTrace();
        }
    }

    private void getAllNewsBitcoin(AppController ctrl) {
        //System.out.println(ctrl.getAllNewsBitcoin());
        //setOutputText(ctrl.getAllNewsBitcoin().toString());
        //setOutputText(formatOutput(ctrl.getAllNewsBitcoin()));
        try {
            setOutputText(formatOutput(ctrl.getAllNewsBitcoin().getArticles()));
        } catch (IOException ioe) {
            setOutputText("Couldn't get articles from API...");
            //ioe.printStackTrace();
        }
    }

    private String formatOutput(List<Article> list){
        String text = "";
        for(int i = 0; i < list.size(); i++){
            text += list.get(i);
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
