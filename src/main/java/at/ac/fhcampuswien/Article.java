package at.ac.fhcampuswien;

public class Article {

    private String author;
    private String title;

    //additional attributes

    //private String source;
    private Source source;
    private String description;
    private String url;
    private String urlToImage;
    private String publishedAt;
    private String content;



    public Article (String author, String title) {
        this.author=author;
        this.title=title;
    }

    public Article (Source source) {
        this.source = source;
    }

    public Article (String author, String title, String description) {
        this.author=author;
        this.title=title;
        this.description=description;
    }

    /*public Article(String author, String title, String name, String description, String url, String urlToImage, String publishedAt, String content) {
        super();
        this.name=name;
        this.description=description;
        this.url=url;
        this.urlToImage=urlToImage;
        this.publishedAt=publishedAt;
        this.content=content;
    }*/

    public Source getSource() {
        return source;
    }

    public String getDescription() {
        return description;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getAuthor () {
        return author;
    }

    public String getTitle () {
        return title;
    }

    @Override
    public String toString() {
        return "Title: " + this.title + "  Author: " + this.author + "\n";
    }
}