package at.ac.fhcampuswien;

import at.ac.fhcampuswien.enumparams.Country;
import at.ac.fhcampuswien.enumparams.Endpoint;

import java.io.IOException;
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

    public NewsResponse getTopHeadlinesAustriaGun() throws IOException {

        NewsApi newsApi = new NewsApi();
        newsApi.urlBuilder(Endpoint.TOP_HEADLINES.value, "corona", Country.AUSTRIA.value);
        String receivedJson = newsApi.run(newsApi.getRequestedUrl());
        newsApi.deserializeArticles(receivedJson);

        NewsResponse newsResponse = newsApi.deserializeArticles(receivedJson);

        return newsResponse;
    }

    /**
     * method used to get all bitcoin news
     */
    public List<Article> getAllNewsBitcoin() {
        return filterList("bitcoin", articles);
    }

    public NewsResponse getAllNewsBitcoinGun() throws IOException {

        NewsApi newsApi = new NewsApi();
        newsApi.urlBuilder(Endpoint.EVERYTHING.value, "bitcoin");
        String receivedJson = newsApi.run(newsApi.getRequestedUrl());
        newsApi.deserializeArticles(receivedJson);

        NewsResponse newsResponse = newsApi.deserializeArticles(receivedJson);

        return newsResponse;
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

        Article dummy1 = new Article("Mustermann", "Bitcoin to the moooooon");
        Article dummy2 = new Article("Mannmuster", "Blumen");
        Article dummy3 = new Article("Author0", "Article0 bitcoin");
        Article dummy4 = new Article("Author1", "Article1 bitcoin");
        Article dummy5 = new Article("Mustermann", "Bitcoin to the moooooon");
        Article dummy6 = new Article("Mannmuster", "Blumen");  //nix bitcoin

        dummyList.add(dummy1);
        dummyList.add(dummy2);
        dummyList.add(dummy3);
        dummyList.add(dummy4);
        dummyList.add(dummy5);
        dummyList.add(dummy6);

        return dummyList;
    }
}