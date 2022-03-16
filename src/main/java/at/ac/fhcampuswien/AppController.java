package at.ac.fhcampuswien;

import java.util.*;

public class AppController {

    private List<Article> articles;

    public AppController(){
        this.articles = generateMockList();
    }

    /**
     * method used to set articles
     * @param articles
     */
    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }

    /**
     * method used to get number of articles
     */
    public int getArticleCount() {
        return this.articles != null ? this.articles.size() : 0;
    }

    /**
     * method used to get top headlines in austria
     */
    public List<Article> getTopHeadlinesAustria() {
        if(this.articles != null) return this.articles;
        return new ArrayList<Article>();
    }

    /**
     * method used to get all bitcoin news
     */
    public List<Article> getAllNewsBitcoin() {
        return filterList("bitcoin", articles);
    }

    /**
     * used to filter a List of Articles with a keyword (query)
     * @param query
     * @param articles
     * @return
     */
    protected static List<Article> filterList (String query, List<Article> articles) {
        //filter list for specified query...
        List<Article> filteredList = new ArrayList<>();

        //iterate through articles and add elements that match query to filteredList
        for (int i=0; i<articles.size(); i++) {
            if (articles.get(i).getTitle().toLowerCase().contains(query.toLowerCase())) {
                filteredList.add(articles.get(i));
            }
        }
        return filteredList;
    }

    private static List<Article> generateMockList() {

        List<Article> dummyList = new ArrayList<>();

        Article dummy1 = new Article();
        Article dummy2 = new Article();
        Article dummy3 = new Article();
        Article dummy4 = new Article();

        //to test filtering:
        Article dummy5 = new Article("Mustermann", "Bitcoin to the moooooon");
        Article dummy6 = new Article("Mannmuster", "Blumen");  //nix bitcoin

        dummyList.add(dummy1);
        dummyList.add(dummy2);
        dummyList.add(dummy3);
        dummyList.add(dummy4);
        //dummyList.articles.add(dummy5);
        //dummyList.articles.add(dummy6);

        return dummyList;
    }
}


