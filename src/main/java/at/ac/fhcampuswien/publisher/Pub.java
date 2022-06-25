package at.ac.fhcampuswien.publisher;

public interface Pub {
    void subscriber(Sub s);
    void unsubscribe(Sub s);
    void notifySubscribers();
}
