package at.ac.fhcampuswien.controller;

import at.ac.fhcampuswien.analytics.StreamFilters;
import at.ac.fhcampuswien.articleStructure.Article;
import at.ac.fhcampuswien.news.NewsApiException;
import at.ac.fhcampuswien.publisher.Channel;
import at.ac.fhcampuswien.publisher.Subscriber;

import java.util.List;

public class Actions {

    private static final String INVALID_INPUT_MESSAGE = "Invalid input! Please enter an existing option!";
    private static final String EXIT_MESSAGE = "Bye bye!";
    private final AppController ctrl = AppController.getInstance();
    private static Actions instance = null;
    private final Channel channel = Channel.getInstance();
    //private final Subscriber subscriber = new Subscriber();

    /*
    {
        channel.subscriber(subscriber);
    }
     */

    private Actions(){}

    public static Actions getInstance(){
        if(instance == null){
            instance = new Actions();
        }
        return instance;
    }


    protected void getArticleCount() throws NewsApiException {
        int count = ctrl.getArticles().size();
        List<Article> articleList = ctrl.getArticles();
        StreamFilters streamFilter = StreamFilters.getInstance();
        try{
            //setOutputText

            channel.setNews("Number of articles: " + count + "\n" +
                    "Most articles provided by: " + streamFilter.sourceWithMostArticles(articleList) + "\n" +
                    "Author with longest name: " + streamFilter.authorWithLongestName(articleList) + "\n" +
                    "Number of articles by NYT: " + streamFilter.numberOfArticlesNyt(articleList) + "\n" +
                    "Number of articles with headlines less than 15 characters: " + streamFilter.articlesWithHeadlineSub15Chars(articleList) + "\n" +
                    "Headlines with less than 15 characters: " + formatOutput(streamFilter.articlesWithHeadlineSub15CharsList(articleList)));
        } catch (NullPointerException e){
            throw new NewsApiException("Request articles before use this operation");
        }
    }

    protected void getTopHeadlinesAustria() throws NewsApiException {
        List<Article> articles = ctrl.getTopHeadlinesAustria().getArticles();
        channel.setNews(formatOutput(articles));
        ctrl.setArticles(articles);
    }

    protected void getAllNewsBitcoin() throws NewsApiException {
        List<Article> articles = ctrl.getAllNewsBitcoin().getArticles();
        channel.setNews(formatOutput(StreamFilters.getInstance().articlesSortedByLengthThenAlphabetically(articles)));
        ctrl.setArticles(articles);
    }

    protected void getCustomHeadlines(Menu menu) throws NewsApiException {
        List<Article> articles = ctrl.getCustomHeadlines(menu).getArticles();
        channel.setNews(formatOutput(articles));
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

    protected static void printExitMessage() {
        System.out.println(EXIT_MESSAGE);
        System.exit(0);
    }

    protected static void printInvalidInputMessage() {
        System.out.println(INVALID_INPUT_MESSAGE);
    }

    protected static void printMenu(){
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
