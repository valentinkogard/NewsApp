package at.ac.fhcampuswien;

public class Article {

    private String author;

    private String title;

    public Article (String author, String title) {

    }

    public String getAuthor (String author) {
        return author;
    }

    public String getTitle (String title) {
        return title;
    }

    @Override
    public String toString() {
        return "Article{" +
                "author='" + author + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
