package at.ac.fhcampuswien;

import java.util.*;

public class AppController {

    private List<Article> articles;

    public AppController(){

        this.articles = generateMockList();

    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }

    public int getArticleCount() {

        return 0;

    }

    public List<Article> getTopHeadlinesAustria() {
        return articles;
    }

    public List<Article> getAllNewsBitcoin() {
        return articles;
    }

    protected static List<Article> filterList (String query, List<Article> articles) {

        //filter list for specified query...
        return articles;

    }

    private static List<Article> generateMockList() {

        List<Article> dummyList = new ArrayList<>();

        Article dummy1 = new Article();
        Article dummy2 = new Article();
        Article dummy3 = new Article();
        Article dummy4 = new Article();

        //to test filtering:
        //Article dummy5 = new Article("Mustermann", "Bitcoin to the moooooon");
        //Article dummy6 = new Article("Mannmuster", "Blumen");  //nix bitcoin

        dummyList.add(dummy1);
        dummyList.add(dummy2);
        dummyList.add(dummy3);
        dummyList.add(dummy4);
        //dummyList.articles.add(dummy5);
        //dummyList.articles.add(dummy6);

        return dummyList;
    }
}


