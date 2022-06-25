package at.ac.fhcampuswien.news;

import java.io.IOException;

import at.ac.fhcampuswien.enumparams.Endpoint;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import com.google.gson.*;

public class NewsApi {

    private static final String key = "be9de4191f3745f1bbad19153c6ca440"; //hab selber noch einen key geholt
    private String requestedUrl;
    private static NewsApi instance = null;

    private NewsApi(){};

    public static NewsApi getInstance(){
        if(instance == null){
            instance = new NewsApi();
        }
        return instance;
    }

    public void setRequestedUrl(String requestedUrl) {
        this.requestedUrl = requestedUrl;
    }

    public String getRequestedUrl() {
        return requestedUrl;
    }

    public void urlBuilder(String endpoint, String category, String country, String query, String language, String sortBy) throws NewsApiException {
        if(endpoint.equals(Endpoint.TOP_HEADLINES.value)){
            String build = "https://newsapi.org/v2/"+endpoint+"?category="+category+"&country="+country+"&apiKey="+key;
            setRequestedUrl(build);
        }
        else if(endpoint.equals(Endpoint.EVERYTHING.value)){
            String build = "https://newsapi.org/v2/"+endpoint+"?q="+query+"&language="+language+"&sortBy="+sortBy+"&apiKey="+key;
            setRequestedUrl(build);
        } else {
            throw new NewsApiException("Endpoint not known in urlBuilder");
        }
    }

    public String run(String urlString) throws NewsApiException {

        Request request = new Request.Builder()
                .url(urlString)
                .build();

        OkHttpClient client = new OkHttpClient();

        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        } catch (IOException ioe) {
            //System.out.println(ioe.getMessage());
            throw new NewsApiException("No internet connection available!");
        }
    }

    public NewsResponse deserializeArticles(String receivedJson) throws NewsApiException {
        try {
            return new Gson().fromJson(receivedJson, NewsResponse.class);
        } catch (JsonSyntaxException n) {
            n.printStackTrace();
            throw new NewsApiException("Articles could not be processed successfully");
        }
    }

}
