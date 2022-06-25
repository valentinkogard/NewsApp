package at.ac.fhcampuswien.publisher;

import at.ac.fhcampuswien.controller.Menu;

import java.util.ArrayList;
import java.util.List;

public class Channel implements Pub {

    private String news;
    private final List<Sub> channels = new ArrayList<>();
    private Menu menu;

    private static Channel instance = null;

    private Channel(){}

    public static Channel getInstance(){
        if(instance == null){
            instance = new Channel();
        }
        return instance;
    }

    @Override
    public void subscriber(Sub s) {
        this.channels.add(s);
    }

    @Override
    public void unsubscribe(Sub s) {
        this.channels.remove(s);
    }

    @Override
    public void notifySubscribers() {
        for(Sub s : channels){
            s.update(this.menu, this.news);
        }
    }

    public void setNews(String news){
        this.news = news;
        notifySubscribers();
    }

    public void setMenu(Menu menu){
        this.menu = menu;
    }
}
