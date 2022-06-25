package at.ac.fhcampuswien.publisher;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Flow.*;

public class NewsAgency implements Publisher {

    private String news;
    private final List<Subscriber> channels = new ArrayList<>();

    @Override
    public void subscriber(Subscriber s) {
        this.channels.add(s);
    }

    @Override
    public void unsubscribe(Subscriber s) {
        this.channels.remove(s);
    }

    @Override
    public void notifySubscribers() {
        for(Subscriber s : channels){
            s.update(this.news);
        }
    }

    public void setNews(String news){
        this.news = news;
        notifySubscribers();
    }
}
