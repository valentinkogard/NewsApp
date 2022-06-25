package at.ac.fhcampuswien.publisher;

import java.util.concurrent.Flow.*;

public interface Publisher {
    void subscriber(Subscriber s);
    void unsubscribe(Subscriber s);
    void notifySubscribers();
}
