package at.ac.fhcampuswien.enumparams;

public enum Sortby {

    relevancy("relevancy"),
    popularity("popularity"),
    publishedAt("publishedAt");

    public final String value;

    Sortby (String value) {
        this.value=value;
    }
}
