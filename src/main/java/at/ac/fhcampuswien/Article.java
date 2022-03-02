package at.ac.fhcampuswien;

public class Article {

    private String author;

    private String title;

    public Article () {

    }

    public String getAuthor () { return author; }

    public String getTitle () {
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
