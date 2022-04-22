package at.ac.fhcampuswien;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import at.ac.fhcampuswien.enumparams.Endpoint;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import com.google.gson.*;

public class NewsApi {

    private String key = "be9de4191f3745f1bbad19153c6ca440"; //hab selber noch einen key geholt

    private String requestedUrl;


    public void setRequestedUrl(String requestedUrl) {
        this.requestedUrl = requestedUrl;
    }

    public String getKey() {
        return key;
    }

    public String getRequestedUrl() {
        return requestedUrl;
    }

    public void urlBuilder(String endpoint, String query, String country, String sortBy, String category) {
        String build = "https://newsapi.org/v2/"+endpoint+"?q="+query+"&country="+country+"&sortBy="+sortBy+"&category"+category+"&apiKey="+this.getKey();
        setRequestedUrl(build);
    }

    public void urlBuilder(String endpoint, String query, String country, String sortBy) {
        String build = "https://newsapi.org/v2/"+endpoint+"?q="+query+"&country="+country+"&sortBy="+sortBy+"&apiKey="+this.getKey();
        setRequestedUrl(build);
    }

    public void urlBuilder(String endpoint, String query, String country) {
        String build = "https://newsapi.org/v2/"+endpoint+"?q="+query+"&country="+country+"&apiKey="+this.getKey();
        setRequestedUrl(build);
    }

    public void urlBuilder(String endpoint, String query) {
        String build = "https://newsapi.org/v2/"+endpoint+"?q="+query+"&apiKey="+this.getKey();
        setRequestedUrl(build);
    }



    public String run(String urlString) throws IOException {

        Request request = new Request.Builder()
                .url(urlString)
                .build();

        OkHttpClient client = new OkHttpClient();

        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }



    public NewsResponse deserializeArticles(String receivedJson) {
        return new Gson().fromJson(receivedJson, NewsResponse.class);

        /*System.out.println(newsResponse.getStatus());
        System.out.println(newsResponse.getTotalResults());
        System.out.println(newsResponse.getArticles());
        System.out.println(newsResponse.getArticles().get(0).getDescription());*/

        /*JsonParser parser = new JsonParser();
        JsonObject convertedObject = (JsonObject) parser.parse(String.valueOf(receivedJson));
        NewsResponse newsResponse = new Gson().fromJson(convertedObject, NewsResponse.class);*/

        /*JsonParser parser = new JsonParser();
        JsonObject convertedObject = (JsonObject) parser.parse(receivedJson);

        NewsResponse newsResponse = new NewsResponse();
        newsResponse.setStatus(convertedObject.get("status").toString());
        newsResponse.setTotalResults(convertedObject.get("totalResults").getAsInt());

        JsonArray jsonArray = convertedObject.getAsJsonArray("articles");
        int length = jsonArray.size();

       ArrayList<String> articles = new ArrayList<>(); //STRING!!!!!!!!

        ArrayList<Article> articleList = new ArrayList<>();

        for (int i=0; i<length; i++){
            JsonObject jsonObject = (JsonObject) jsonArray.get(i);
            articles.add(jsonObject.toString());
            Article newArticle = new Article(
                    jsonObject.get("author").toString(),
                    jsonObject.get("title").toString());

            articleList.add(newArticle);

        }

        newsResponse.setArticles(articleList);

        System.out.println(receivedJson);
        System.out.println(newsResponse.getStatus());
        System.out.println(newsResponse.getTotalResults());
        System.out.println(length);
        System.out.println(articles.get(3));
        System.out.println(articleList.get(3));
        System.out.println(articleList.get(0));
        System.out.println(articleList.get(19).getTitle());*/
    }

}
