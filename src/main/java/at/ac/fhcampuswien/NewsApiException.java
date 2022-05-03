package at.ac.fhcampuswien;

public class NewsApiException extends Exception{

    NewsApiException() {
        super("No internet connection available!");
    }
}
