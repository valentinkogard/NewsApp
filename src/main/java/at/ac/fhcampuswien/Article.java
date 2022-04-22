package at.ac.fhcampuswien;

public class Article {

    private String author;
    private String title;

    //additional attributes

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

    /*public Article(String author, String title, String name, String description, String url, String urlToImage, String publishedAt, String content) {
        super();
        this.name=name;
        this.description=description;
        this.url=url;
        this.urlToImage=urlToImage;
        this.publishedAt=publishedAt;
        this.content=content;
    }*/

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