package at.ac.fhcampuswien.enumparams;

public enum Endpoint {

    TOP_HEADLINES ("top-headlines"),
    EVERYTHING("everything");

    public final String value;

    Endpoint (String value) {
        this.value=value;
    }

}
