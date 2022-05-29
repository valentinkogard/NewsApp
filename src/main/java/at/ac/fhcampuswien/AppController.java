package at.ac.fhcampuswien;

import at.ac.fhcampuswien.enumparams.Country;
import at.ac.fhcampuswien.enumparams.Endpoint;
import at.ac.fhcampuswien.enumparams.Language;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.*;
import java.util.List;

import org.apache.commons.lang3.EnumUtils;

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

        NewsApi newsApi = new NewsApi();
        newsApi.urlBuilder(Endpoint.TOP_HEADLINES.value, "", Country.AUSTRIA.value);
        String receivedJson = newsApi.run(newsApi.getRequestedUrl());
        newsApi.deserializeArticles(receivedJson);

        NewsResponse newsResponse = newsApi.deserializeArticles(receivedJson);

        return newsResponse;
    }

    /**
     * method used to get all bitcoin news
     */
    public NewsResponse getAllNewsBitcoin() throws NewsApiException {

        NewsApi newsApi = new NewsApi();
        newsApi.urlBuilder(Endpoint.EVERYTHING.value, "bitcoin");
        String receivedJson = newsApi.run(newsApi.getRequestedUrl());
        newsApi.deserializeArticles(receivedJson);

        NewsResponse newsResponse = newsApi.deserializeArticles(receivedJson);

        return newsResponse;
    }

    public NewsResponse getCustomHeadlines(FXMLLoader fxmlLoader) throws NewsApiException {

        NewsApi newsApi = new NewsApi();
        String endpoint, category, sortBy, country, language = "";

        ComboBox endpointBox = (ComboBox) fxmlLoader.getNamespace().get("endpointBox");
        try {
            endpoint = endpointBox.getSelectionModel().getSelectedItem().toString();
        } catch (NullPointerException n) {
            throw new NewsApiException("Please select endpoint!");
        } catch (Exception e) {
            throw new NewsApiException(e.getMessage());
        }

        ComboBox categoryBox = (ComboBox) fxmlLoader.getNamespace().get("categoryBox");
        category = categoryBox.getSelectionModel().getSelectedItem().toString();
        TextField countryText = (TextField) fxmlLoader.getNamespace().get("countryText");
        country = countryText.getText();


        ComboBox sortbyBox = (ComboBox) fxmlLoader.getNamespace().get("sortbyBox");
        sortBy = sortbyBox.getSelectionModel().getSelectedItem().toString();
        TextField languageText = (TextField) fxmlLoader.getNamespace().get("languageText");
        language = languageText.getText();

        if(EnumUtils.isValidEnum(Country.class, country)) {

        //if (enumContains(Country.values(), country)){
            if(endpoint.equals(Endpoint.TOP_HEADLINES.value)) newsApi.urlBuilderCustomTopHeadlines(endpoint, country, category);
            else if (endpoint.equals(Endpoint.EVERYTHING.value)) newsApi.urlBuilderCustomEverything(endpoint, language, sortBy);
        } else {
            throw new NewsApiException("Enter valid language");
        }



        String receivedJson = newsApi.run(newsApi.getRequestedUrl());
        NewsResponse newsResponse = newsApi.deserializeArticles(receivedJson);

        System.out.println(endpoint+category+sortBy+country+language);

        return newsResponse;
    }
/*
    private <T extends Enum<T>> boolean enumContains(Class enumText, String check) {
        //List<Enum> enumList =  Arrays.asList(enumText);
        //List list = EnumUtils.getEnumList(Country.class);
        //String xyz = EnumUtils.getEnumList(Country.class).get(0).value;
        //Category cat : Category.values()

        for(int i = 0; i < enumText.; i++) {
            if(EnumUtils.getEnumList(enumText.class).get(i).value.equals(check)) {
                return true;
            }
        }
        return false;
        //EnumUtils.isValidEnum(enumText.cl)


    }
*/




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