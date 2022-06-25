package at.ac.fhcampuswien.controller;

import at.ac.fhcampuswien.articleStructure.Article;
import at.ac.fhcampuswien.articleStructure.Source;
import at.ac.fhcampuswien.enumparams.*;
import at.ac.fhcampuswien.news.NewsApi;
import at.ac.fhcampuswien.news.NewsApiException;
import at.ac.fhcampuswien.news.NewsResponse;

import java.util.*;
import java.util.List;

public class AppController {

    private List<Article> articles;
    private NewsApi newsApi = NewsApi.getInstance();
    private static AppController instance = null;

    private AppController(){
        this.articles = generateMockList();
    }

    public static AppController getInstance(){
        if(instance == null){
            instance = new AppController();
        }
        return instance;
    }

    /**
     * method used to set articles
     * @param articles
     */
    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }
    public List<Article> getArticles(){
        return this.articles;
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
    public NewsResponse getTopHeadlinesAustria() throws NewsApiException {

        //NewsApi newsApi = new NewsApi();
        //newsApi.urlBuilder(Endpoint.TOP_HEADLINES.value, "", Country.AUSTRIA.value);
        newsApi.urlBuilder(Endpoint.TOP_HEADLINES.value, "", Country.AUSTRIA.value, null, null, null);
        String receivedJson = newsApi.run(newsApi.getRequestedUrl());
        newsApi.deserializeArticles(receivedJson);

        NewsResponse newsResponse = newsApi.deserializeArticles(receivedJson);
        setArticles(newsResponse.getArticles());

        //MainDownload.getInstance().measureTimeOfDownload();

        if(newsResponse.getArticles().size() == 0){
            throw new NewsApiException("No articles available");
        }
        return newsResponse;
    }

    /**
     * method used to get all bitcoin news
     */
    public NewsResponse getAllNewsBitcoin() throws NewsApiException {

        String query = "bitcoin";
        //NewsApi newsApi = new NewsApi();
        //newsApi.urlBuilder(Endpoint.EVERYTHING.value, "bitcoin");
        newsApi.urlBuilder(Endpoint.EVERYTHING.value, null, null, query, "", "");
        String receivedJson = newsApi.run(newsApi.getRequestedUrl());
        newsApi.deserializeArticles(receivedJson);

        NewsResponse newsResponse = newsApi.deserializeArticles(receivedJson);
        setArticles(newsResponse.getArticles());
        //downloadURLs();

        if(newsResponse.getArticles().size() == 0){
            throw new NewsApiException("No articles available for query: " + query);
        }


        return newsResponse;
    }

    /**
     * checks if entry is listed in corresponding enum
     * @param enums
     * @param check
     * @return
     */
    private String getValidEntry(String enums, String check) {
        switch (enums){
            case "Category":
                for(Category a : Category.values()){
                    if(a.value.equals(check)) return check;
                    if(a.toString().equals(check)) return a.value;
                }
                break;
            case "Country":
                for(Country a : Country.values()){
                    if(a.value.equals(check)) return check;
                    if(a.toString().equals(check)) return a.value;
                }
                break;
            case "Endpoint":
                for(Endpoint a : Endpoint.values()){
                    if(a.value.equals(check)) return check;
                    if(a.toString().equals(check)) return a.value;
                }
                break;
            case "Language":
                for(Language a : Language.values()){
                    if(a.value.equals(check)) return check;
                    if(a.toString().equals(check)) return a.value;
                }
                break;
            case "Sortby":
                for(Sortby a : Sortby.values()){
                    if(a.value.equals(check)) return check;
                    if(a.toString().equals(check)) return a.value;
                }
                break;
            default:
                return "";
        }
        return "";
    }

    /**
     * uses the endpoint "top-headlines" and checks if input is valid
     * @param menu
     * @throws NewsApiException
     */
    private void reqTopHeadlines(Menu menu) throws NewsApiException {
        String country = menu.countryText.getText();
        String category = menu.categoryBox.getSelectionModel().getSelectedItem().toString();

        country = getValidEntry("Country", country);
        category = getValidEntry("Category", category);

        if(country.equals("") || category.equals("")){
            throw new NewsApiException("enter valid request");
        }
        //newsApi.urlBuilderCustomTopHeadlines("top-headlines", country, category);
        newsApi.urlBuilder(Endpoint.TOP_HEADLINES.value, category, country, null, null, null);
    }

    /**
     * uses the endpoint "everything" and checks if input is valid
     * @param menu
     * @throws NewsApiException
     */
    private void reqEverything(Menu menu) throws NewsApiException {
        String language = menu.languageText.getText();
        String sortBy = menu.sortbyBox.getSelectionModel().getSelectedItem().toString();
        String q = menu.qText.getText();

        language = getValidEntry("Language", language);
        sortBy = getValidEntry("Sortby", sortBy);

        if(language.equals("") || sortBy.equals("") || q.equals("")){
            throw new NewsApiException("enter valid request");
        }
        //newsApi.urlBuilderCustomEverything("everything", q, language, sortBy);
        newsApi.urlBuilder(Endpoint.EVERYTHING.value, null, null, q, language, sortBy);
    }

    /**
     * build url using params entered by user
     * @param menu
     * @return
     * @throws NewsApiException
     */
    public NewsResponse getCustomHeadlines(Menu menu) throws NewsApiException {

        //NewsApi newsApi = new NewsApi();
        String endpoint = "";
        try {
            endpoint = menu.endpointBox.getSelectionModel().getSelectedItem().toString();
        } catch (NullPointerException n) {
            throw new NewsApiException("Please select endpoint!");
        } catch (Exception e) {
            throw new NewsApiException(e.getMessage());
        }

        if(endpoint.equals(Endpoint.TOP_HEADLINES.value)) reqTopHeadlines(menu);
        else if(endpoint.equals(Endpoint.EVERYTHING.value)) reqEverything(menu);

        String receivedJson = newsApi.run(newsApi.getRequestedUrl());
        NewsResponse newsResponse = newsApi.deserializeArticles(receivedJson);

        if(newsResponse.getArticles().size() == 0){
            throw new NewsApiException("No articles available");
        }
        return newsResponse;
    }

    /**
     * used to filter a List of Articles with a keyword (query)
     * @param query
     * @param articles
     * @return
     */
    public static List<Article> filterList (String query, List<Article> articles) {
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

        Article dummy1 = new Article.Builder("Mustermann", "Bitcoin to the moooooon")
                .description("dhsflasgd")
                .publishedAt("2022-06-24")
                .source(new Source.Builder().id("xyz").name("abc").build())
                .url("www.hallowelt.com")
                .build();
        Article dummy2 = new Article.Builder("Mannmuster", "Blumen").build();
        Article dummy3 = new Article.Builder("Author0", "Article0 bitcoin").build();
        Article dummy4 = new Article.Builder("Author1", "Article1 bitcoin").build();
        Article dummy5 = new Article.Builder("Mustermann", "Bitcoin to the moooooon").build();
        Article dummy6 = new Article.Builder("Mannmuster", "Blumen").build();  //nix bitcoin

        dummyList.add(dummy1);
        dummyList.add(dummy2);
        dummyList.add(dummy3);
        dummyList.add(dummy4);
        dummyList.add(dummy5);
        dummyList.add(dummy6);

        return dummyList;
    }
}