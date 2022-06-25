package at.ac.fhcampuswien.publisher;

import at.ac.fhcampuswien.controller.Menu;

public class Subscriber implements Sub {

    @Override
    public void update(Menu menu, String news) {
        menu.setOutputText(news);
        //System.out.println("News updated in: " + this + " news: " + news);
        //System.out.println("------------------------------------------------------------");
    }
}
