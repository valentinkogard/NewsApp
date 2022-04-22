package at.ac.fhcampuswien;

import java.io.IOException;

public class App {
    public static void main(String[] args) throws IOException {

        Menu.launch(Menu.class, args);

        /*NewsApi newsApi = new NewsApi();
        String receivedJson = newsApi.run("https://newsapi.org/v2/everything?q=bitcoin&country=&apiKey="+newsApi.getKey());
        newsApi.deserializeArticles(receivedJson);*/

    }

}