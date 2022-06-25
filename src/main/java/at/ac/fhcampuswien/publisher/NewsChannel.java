package at.ac.fhcampuswien.publisher;

public class NewsChannel implements Subscriber{
    @Override
    public void update(String news) {
        System.out.println("News updated in: " + this + " news: " + news);
    }
}
